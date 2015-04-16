package org.uta.steam.bl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.bl.api.AppSteamApi;
import org.uta.steam.bl.crawler.AppWebCrawler;
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

	
	public List<SteamApp> getAllApps() {
		return appDaoService.getAll();
	}
	
	public void setAppUpdateList(List<Long> appIds) {
		appDaoService.setAppUpdateList(appIds);
	}
	
	public SteamApp getWholeApp(long appId) {
		return appDaoService.getWholeAppByAppId(appId);
	}
	
	
	public void updateAppDataFromSteam() {
		for(SteamApp app : appDaoService.getWholeAppsToUpdate()) {
			
			updateAppData(app);
			updateAppVersions(app);
			updateAppDlcs(app);
			
			appDaoService.saveOrUpdate(app);
		}
	}
	
	
	private void updateAppData(SteamApp app) {
		AppData data = new AppData();
		data.setPrice(appWebCrawler.getAppPrice(app.getAppId()));
		data.setTags(new HashSet<String>(appWebCrawler.getAppTags(app.getAppId())));			
		data.setReviews(new HashSet<Review>(appWebCrawler.getHelpfulUserReviews(app.getAppId())));			
		app.getData().add(data);
	}
	
	
	// TODO: we could also just replace the old list with the new one
	private void updateAppVersions(SteamApp app) {
		List<AppVersion> versions = appSteamApi.getVersions(app.getAppId());
		
		// create a list of all versions
		Map<Long,AppVersion> map = new HashMap<Long,AppVersion>();
		for(AppVersion version : versions) {
			map.put(version.getPublished().getTime(), version);
		}

		// filter all versions which are already available
		for(AppVersion version : app.getVersions()) {
			if(map.containsKey(version.getPublished().getTime())) {
				map.remove(version.getPublished().getTime());
			}
		}
		
		// add the new ones
		for(AppVersion version : map.values()) {
			app.getVersions().add(version);
		}
	}
	
	private void updateAppDlcs(SteamApp app) {
		List<AppDLC> dlcs = appWebCrawler.getDLCs(app.getAppId());
		
		// create a list of all dlcs 
		Map<Long,AppDLC> map = new HashMap<Long,AppDLC>();
		for(AppDLC dlc : dlcs) {
			map.put(dlc.getDlcId(), dlc);
		}

		// filter all dlcs which are already available
		for(AppDLC dlc : app.getDlcs()) {
			if(map.containsKey(dlc.getDlcId())) {
				map.remove(dlc.getDlcId());
			}
		}
		
		// add the new ones
		for(AppDLC dlc : map.values()) {
			app.getDlcs().add(dlc);
		}
		
		// update dlc content		
		for(AppDLC dlc : app.getDlcs()) {
			AppData data = new AppData();
			data.setPrice(appWebCrawler.getAppPrice(dlc.getDlcId()));
			data.setTags(new HashSet<String>(appWebCrawler.getAppTags(dlc.getDlcId())));			
			data.setReviews(new HashSet<Review>(appWebCrawler.getHelpfulUserReviews(dlc.getDlcId())));			
			dlc.getData().add(data);
		}
	}

	public void updateAppListFromSteam() {
		appDaoService.updateAppList(new ArrayList<SteamApp>());
	}
}
