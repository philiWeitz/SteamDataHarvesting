package org.uta.steam.bl.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.bl.api.AppSteamApi;
import org.uta.steam.bl.crawler.AppWebCrawler;
import org.uta.steam.bl.csv.CsvExporter;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;

@Resource
class SteamDataServiceImpl implements SteamDataService {

	@Autowired
	private SteamAppDAOService appDaoService;

	private AppSteamApi appSteamApi = new AppSteamApi();
	private AppWebCrawler appWebCrawler = new AppWebCrawler();
	private CsvExporter csvExporter = new CsvExporter();
	
	
	public List<SteamApp> getAllApps() {
		List<SteamApp> result = appDaoService.getAll();

		Set<AppData> emptyDataSet = Collections.emptySet();
		Set<AppDLC> emptyDLCSet = Collections.emptySet();
		Set<AppVersion> emptyVersionSet = Collections.emptySet();

		for (SteamApp app : result) {
			app.setData(emptyDataSet);
			app.setDlcs(emptyDLCSet);
			app.setVersions(emptyVersionSet);
		}
		return result;
	}

	public SteamApp getWholeApp(long appId) {
		return appDaoService.getWholeAppByAppId(appId);
	}

	public void harvestDataFromSteam() {
		for (SteamApp app : appDaoService.getWholeAppsToUpdate()) {

			updateAppData(app);
			updateAppVersions(app);
			updateAppDlcs(app);

			appDaoService.saveOrUpdate(app);
		}
	}

	private void updateAppData(SteamApp app) {
		AppData data = new AppData();
		data.setPrice(appWebCrawler.getAppPrice(app.getAppId()));
		data.setTags(new HashSet<String>(appWebCrawler.getAppTags(app
				.getAppId())));
		data.setReviews(new HashSet<Review>(appSteamApi
				.getHelpfulAppReviews(app.getAppId())));
		app.getData().add(data);
	}

	// TODO: we could also just replace the old list with the new one
	private void updateAppVersions(SteamApp app) {
		List<AppVersion> versions = appSteamApi.getVersions(app.getAppId());

		// create a list of all versions
		Map<Long, AppVersion> map = new HashMap<Long, AppVersion>();
		for (AppVersion version : versions) {
			map.put(version.getPublished().getTime(), version);
		}

		// filter all versions which are already available
		for (AppVersion version : app.getVersions()) {
			if (map.containsKey(version.getPublished().getTime())) {
				map.remove(version.getPublished().getTime());
			}
		}

		// add the new ones
		for (AppVersion version : map.values()) {
			app.getVersions().add(version);
		}
	}

	private void updateAppDlcs(SteamApp app) {
		List<AppDLC> dlcs = appWebCrawler.getDLCs(app.getAppId());

		// create a list of all dlcs
		Map<Long, AppDLC> map = new HashMap<Long, AppDLC>();
		for (AppDLC dlc : dlcs) {
			map.put(dlc.getDlcId(), dlc);
		}

		// filter all dlcs which are already available
		for (AppDLC dlc : app.getDlcs()) {
			if (map.containsKey(dlc.getDlcId())) {
				map.remove(dlc.getDlcId());
			}
		}

		// add the new ones
		for (AppDLC dlc : map.values()) {
			app.getDlcs().add(dlc);
		}

		// update dlc content
		for (AppDLC dlc : app.getDlcs()) {
			AppData data = new AppData();
			data.setPrice(appWebCrawler.getAppPrice(dlc.getDlcId()));
			data.setTags(new HashSet<String>(appWebCrawler.getAppTags(dlc
					.getDlcId())));
			data.setReviews(new HashSet<Review>(appSteamApi
					.getHelpfulAppReviews(dlc.getDlcId())));
			dlc.getData().add(data);
		}
	}

	public void updateAppListFromSteam() {
		appDaoService.updateAppList(appSteamApi.getApps());
	}

	public boolean addAppToUpdateList(Long appId) {
		return appDaoService.addAppToUpdateList(appId);
	}

	public boolean removeAppFromUpdateList(Long appId) {
		return appDaoService.removeAppFromUpdateList(appId);
	}

	public String createCsvFile(long appId) {
		SteamApp app = appDaoService.getWholeAppByAppId(appId);
		
		if(null != app) {
			return csvExporter.getCsvFileByApp(app);
		}
		return null;
	}
}
