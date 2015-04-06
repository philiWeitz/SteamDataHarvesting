package org.uta.steam.bl.test.api;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.uta.steam.bl.api.UserSteamApi;
import org.uta.steam.bl.ui.UserAchievement;
import org.uta.steam.bl.ui.UserApp;

public class UserSteamApiTest {

	private static final String USER_ID = "76561198078678436";
	private static final long APP_ID = 730;	
	
	@Test
	public void getUserGamesTest() {
		UserSteamApi userSteamApi = new UserSteamApi();
		
		List<UserApp> userApps = userSteamApi.getGames(USER_ID);
		assertFalse(userApps.isEmpty());
	}


	@Test
	public void getUserAchievementsByAppIdTest() {
		UserSteamApi userSteamApi = new UserSteamApi();
		
		UserAchievement userAchievement = 
				userSteamApi.getUserAchievementsByAppId(USER_ID, APP_ID);
		
		assertTrue(userAchievement.getMaximumAchievements() > 0);
		assertTrue(userAchievement.getAchieved() > 0);	
		assertNotNull(userAchievement.getTimestamp());
		assertEquals(APP_ID, userAchievement.getAppId());
	}
	
	
	@Test
	public void getUserStatisticsByAppIdTest() {
		UserSteamApi userSteamApi = new UserSteamApi();
		
		Map<String,Long> statMap =
				userSteamApi.getUserStatisticsByAppId(USER_ID, APP_ID);
		
		assertFalse(statMap.isEmpty());
	}
}
