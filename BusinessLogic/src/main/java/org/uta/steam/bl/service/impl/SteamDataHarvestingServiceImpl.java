package org.uta.steam.bl.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uta.steam.bl.api.AppSteamApi;
import org.uta.steam.bl.crawler.AppWebCrawler;
import org.uta.steam.bl.service.SteamDataHarvestingService;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;

@Service
class SteamDataHarvestingServiceImpl implements SteamDataHarvestingService {

	@Autowired
	private SteamAppDAOService appDaoService;
	
	private AppSteamApi appSteamApi = new AppSteamApi();
	private AppWebCrawler appWebCrawler = new AppWebCrawler();


	public SteamApp getWholeApp(long appId) {
		return appDaoService.getWholeAppByAppId(appId);
	}

	
	public void harvestDataFromSteam() {
		for (SteamApp app : appDaoService.getWholeAppsToUpdate()) {

			updateAppData(app);
			updateAppVersions(app);
			updateAppDlcs(app);
			addReviews(app);
			app.setHasData(true);

			appDaoService.saveOrUpdate(app);
		}
	}
	

	public void updateAppListFromSteam() {
		appDaoService.updateAppList(appSteamApi.getApps());
	}

	
	private void addReviews(SteamApp app) {

		List<AppVersion> versions = new LinkedList<AppVersion>(app.getVersions());
		Collections.sort(versions);
		Collections.reverse(versions);
		
		// remove duplicated reviews
		List<Review> newReviews = appSteamApi.getHelpfulAppReviews(app.getAppId());
		newReviews = removeDuplicatedReviews(app.getReviews(), newReviews);

		for(Review review : newReviews) {
			review.setVersion(getAppVersion(versions, review));
		}
		
		app.getReviews().addAll(newReviews);
	}

	private void updateAppData(SteamApp app) {
		AppData data = new AppData();
		data.setPrice(appWebCrawler.getAppPrice(app.getAppId()));
		data.setTags(new HashSet<String>(appWebCrawler.getAppTags(app.getAppId())));
		app.getData().add(data);
	}


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
			
			// remove duplicated reviews
			List<Review> newReviews = appSteamApi.getHelpfulAppReviews(dlc.getDlcId());
			newReviews = removeDuplicatedReviews(dlc.getReviews(), newReviews);

			// add new reviews (version is always null)
			dlc.getReviews().addAll(newReviews);
			
			// add app data
			AppData data = new AppData();
			data.setPrice(appWebCrawler.getAppPrice(dlc.getDlcId()));
			data.setTags(new HashSet<String>(appWebCrawler.getAppTags(dlc.getDlcId())));
			dlc.getData().add(data);
		}
	}

	
	private AppVersion getAppVersion(List<AppVersion> versions, Review review) {
		for(AppVersion version : versions) {
			Date date;
			
			if(null != review.getUpdated()) {
				date = review.getUpdated();
			}  else {
				date = review.getPosted();
			}
			
			// 0 or less means that the publish date is equal or earlier then the date
			if(version.getPublished().compareTo(date) <= 0) {
				return version;
			}
		}
		
		return null;
	}
	
	
	private List<Review> removeDuplicatedReviews(Set<Review> dbSet, List<Review> newReviews) {
		List<Review> result = new LinkedList<Review>();

		// create a hash set to improve performance 
		Set<Integer> reviewHash = new HashSet<Integer>();
		for(Review review : dbSet) {
			reviewHash.add(review.hashCode());
		}
				
		// remove duplicated reviews
		for(Review review : newReviews) {
			if(!reviewHash.contains(review.hashCode())) {
				result.add(review);
			}
		}
		return result;
	}
}
