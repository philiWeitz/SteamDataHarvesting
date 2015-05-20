package org.uta.steam.bl.service;

import java.util.Date;
import java.util.List;

import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;

public interface SteamDataService {
	List<SteamApp> getAllAppsAndUpdateList(String searchTerm, int max);
	
	boolean addAppToUpdateList(Long appId);
	boolean removeAppFromUpdateList(Long appId);
	
	SteamApp getWholeApp(long appId);

	void harvestDataFromSteam();

	void updateAppListFromSteam();
	
	String createCsvFile(long appId);
	
	List<AppVersion> getVersionsByAppId(long appId);
	
	List<Review> getReviewByAppIdAndVersionId(long appId, Date published);	
}
