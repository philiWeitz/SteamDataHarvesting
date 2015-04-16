package org.uta.steam.jpa.service.impl;

import java.util.Date;

import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;



public class TestDataServiceImpl {

	private SteamApp appNoData = null;
	private SteamApp appWithData = null;
	
	
	private SteamAppDAOService appDaoService = new SteamAppDAOServiceImpl();

	
	public void createTestData() {
		cleanDatabase();
		createAppTestData();
	}
	
	private void cleanDatabase() {
		for(SteamApp app : appDaoService.getAll()) {
			appDaoService.delete(app.getId());
		}
	}
	
	private void createAppTestData() {

		SteamApp app1 = new SteamApp();
		app1.setAppId(1);
		app1.setName("App No Data");
		appNoData = appDaoService.saveOrUpdate(app1);
		
		SteamApp app2 = new SteamApp();
		app2.setAppId(226840);
		app2.setName("App With Data");

		// set versions
		AppVersion version = new AppVersion();
		version.setTitle("Version 1");
		version.setContent("Version 1 Content");
		version.setPublished(new Date());
		app2.getVersions().add(version);
		
		// set app data
		AppData data1 = new AppData();
		data1.setPrice(19.99);
		data1.getTags().add("TAG1");
		data1.getTags().add("TAG2");
		data1.getTags().add("TAG3");

		Review appReview1 = new Review();
		appReview1.setAuthorId(1);
		appReview1.setAuthor("author review game 1");
		appReview1.setContent("content review game 1");
		appReview1.setPeopleSeen(100);
		appReview1.setPeopleHelpful(50);
		appReview1.setPosted(new Date());
		data1.getReviews().add(appReview1);
		
		app2.getData().add(data1);
		
		// set DLCs
		AppDLC dlc1 = new AppDLC();
		dlc1.setDlcId(123);
		dlc1.setName("DLC 1 for game 1");
		dlc1.setReleaseDate(new Date());
		
		AppData dlcData = new AppData();
		dlcData.setPrice(19.99);
		dlcData.getTags().add("TAG4");
		dlcData.getTags().add("TAG5");
				
		Review dlcReview = new Review();
		dlcReview.setAuthorId(1);
		dlcReview.setAuthor("author review dlc 1");
		dlcReview.setContent("content review dlc 1");
		dlcReview.setPeopleSeen(22);
		dlcReview.setPeopleHelpful(16);
		dlcReview.setPosted(new Date());
		dlcData.getReviews().add(dlcReview);
		app2.getData().add(dlcData);
		
		app2.getDlcs().add(dlc1);
		appWithData = appDaoService.saveOrUpdate(app2);
	}

	public SteamApp getAppNoData() {
		return appNoData;
	}

	public SteamApp getAppWithData() {
		return appWithData;
	}
}
