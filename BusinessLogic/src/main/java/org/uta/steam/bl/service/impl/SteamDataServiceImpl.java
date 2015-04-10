package org.uta.steam.bl.service.impl;

import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.bl.api.AppSteamApi;
import org.uta.steam.bl.api.UserSteamApi;
import org.uta.steam.bl.crawler.GameWebCrawler;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.SteamUser;
import org.uta.steam.jpa.model.UserAchievementSummary;
import org.uta.steam.jpa.model.UserApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;
import org.uta.steam.jpa.model.service.SteamUserDAOService;

@Resource
class SteamDataServiceImpl implements SteamDataService {

	
	@Autowired
	private SteamUserDAOService userDaoService;
	
	@Autowired
	private SteamAppDAOService appDaoService;

	
	private UserSteamApi userSteamApi = new UserSteamApi(); 
	
	private AppSteamApi appSteamApi = new AppSteamApi();
	private GameWebCrawler appWebCrawler = new GameWebCrawler();
	
	
	public SteamUser extractUserData(String userId) {
		SteamUser user = new SteamUser();
		
		user.setUserId(userId);
		user.setUserName(userSteamApi.getUserName(userId));
		user.setApps(userSteamApi.getOwnedGames(user.getUserId()));
	
		for(UserApp app : user.getApps()) {
			UserAchievementSummary achievement = 
					userSteamApi.getUserAchievementsByAppId(user.getUserId(), app.getAppid());
			user.getAchievements().add(achievement);
		}
		
		return user;
	}
	
	public SteamUser saveUserToDatabase(SteamUser user) {
		return userDaoService.saveOrUpdate(user);
	}
	
	
	public SteamApp extractAppData(long appId) {
		SteamApp app = new SteamApp();
		
		app.setAppid(appId);
		app.setName(appDaoService.getAppNameByAppId(appId));
		app.setPrice(appWebCrawler.getAppPrice(appId));
		app.setVersions(new HashSet<AppVersion>(appSteamApi.getVersions(appId)));
		app.setDlcs(new HashSet<String>(appWebCrawler.getDLCsFromHtmlString(appId)));
		app.setTags(new HashSet<String>(appWebCrawler.getAppTags(appId)));		

		return app;
	}
	
	public SteamApp saveAppToDatabase(SteamApp app) {
		return appDaoService.saveOrUpdate(app);
	}	
}
