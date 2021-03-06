package org.uta.steam.bl.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;

public interface SteamDataService {
	List<SteamApp> getAllAppsAndUpdateList(String searchTerm, int max);
	
	boolean addAppToUpdateList(Long appId);
	
	boolean removeAppFromUpdateList(Long appId);
	
	File createCsvFile(long appId);
	
	List<AppVersion> getVersionsByAppId(long appId);
	
	List<Review> getReviewByAppIdAndVersionId(long appId, Date published);	
	
	List<Review> getReviewsByDlcId(long dlcId);
	
	List<AppDLC> getDlcsByAppId(long appId);
	
	List<SteamApp> getAppsWhichHaveData();
	
	List<AppData> getAppDataById(long appId);
}
