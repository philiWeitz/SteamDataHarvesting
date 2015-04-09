package org.uta.steam.bl.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.bl.api.UserSteamApi;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.SteamUser;
import org.uta.steam.jpa.model.UserAchievementSummary;
import org.uta.steam.jpa.model.UserApp;
import org.uta.steam.jpa.model.service.SteamUserDAOService;

@Resource
class SteamDataServiceImpl implements SteamDataService {

	
	@Autowired
	private SteamUserDAOService userDaoService;
	
	private UserSteamApi userSteamApi = new UserSteamApi(); 
	
	
	public SteamUser extractUserData(String userId) {
		SteamUser user = new SteamUser();
		
		user.setUserId(userId);
		user.setUserName(userSteamApi.getUserName(userId));
		user.setApps(userSteamApi.getGames(user.getUserId()));
	
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
}
