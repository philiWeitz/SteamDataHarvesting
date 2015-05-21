package org.uta.steam.bl.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uta.steam.bl.csv.CsvExporter;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.AppDlcDAOService;
import org.uta.steam.jpa.model.service.AppVersionDAOService;
import org.uta.steam.jpa.model.service.ReviewDAOService;
import org.uta.steam.jpa.model.service.SteamAppDAOService;

@Service
class SteamDataServiceImpl implements SteamDataService {

	@Autowired
	private SteamAppDAOService appDaoService;
	@Autowired
	private AppVersionDAOService versionDaoService;
	@Autowired
	private ReviewDAOService reviewDaoService;
	@Autowired
	private AppDlcDAOService dlcDaoService;	
	
	
	private CsvExporter csvExporter = new CsvExporter();
	

	public SteamApp getWholeApp(long appId) {
		return appDaoService.getWholeAppByAppId(appId);
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

	
	public List<SteamApp> getAllAppsAndUpdateList(String searchTerm, int max) {
		return appDaoService.getAllAppsAndUpdateList(searchTerm, max);
	}
	

	public List<AppVersion> getVersionsByAppId(long appId) {
		return versionDaoService.getVersionsByAppId(appId);
	}

	
	public List<Review> getReviewByAppIdAndVersionId(long appId, Date published) {
		return reviewDaoService.getReviewByAppIdAndVersionId(appId, published);
	}


	public List<Review> getReviewsByDlcId(long dlcId) {
		return reviewDaoService.getReviewsByDlcId(dlcId);
	}


	public List<AppDLC> getDlcsByAppId(long appId) {
		return dlcDaoService.getDlcsByAppId(appId);
	}


	public List<SteamApp> getAppsWhichHaveData() {
		return appDaoService.getAppsWhichHaveData();
	}
}
