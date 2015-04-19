package org.uta.steam.bl.test.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.uta.steam.bl.api.UserSteamApi;
import org.uta.steam.bl.test.util.SteamTestUtil;
import org.uta.steam.jpa.model.UserAchievementSummary;
import org.uta.steam.jpa.model.UserApp;

public class UserSteamApiTest {

	@Test
	public void getUserNameTest() {
		UserSteamApi userSteamApi = new UserSteamApi();

		String userName = userSteamApi.getUserName(SteamTestUtil.USER_ID);
		assertFalse(userName.isEmpty());
	}

	@Test
	public void getUserGamesTest() {
		UserSteamApi userSteamApi = new UserSteamApi();

		List<UserApp> userApps = userSteamApi
				.getOwnedGames(SteamTestUtil.USER_ID);
		assertFalse(userApps.isEmpty());
	}

	@Test
	public void getUserAchievementsByAppIdTest() {
		UserSteamApi userSteamApi = new UserSteamApi();

		UserAchievementSummary userAchievement = userSteamApi
				.getUserAchievementsByAppId(SteamTestUtil.USER_ID,
						SteamTestUtil.APP_ID);

		assertTrue(userAchievement.getMaximumAchievements() >= userAchievement
				.getAchieved());
		assertTrue(userAchievement.getAchieved() > 0);
		assertNotNull(userAchievement.getTimestamp());
		assertEquals(SteamTestUtil.APP_ID, userAchievement.getAppId());
	}

	@Test
	public void getUserStatisticsByAppIdTest() {
		UserSteamApi userSteamApi = new UserSteamApi();

		Map<String, Long> statMap = userSteamApi.getUserStatisticsByAppId(
				SteamTestUtil.USER_ID, SteamTestUtil.APP_ID);

		assertFalse(statMap.isEmpty());
	}
}
