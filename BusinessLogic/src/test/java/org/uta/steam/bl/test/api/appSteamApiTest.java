package org.uta.steam.bl.test.api;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.uta.steam.bl.api.AppSteamApi;
import org.uta.steam.bl.test.util.SteamTestUtil;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;

public class appSteamApiTest {

	@Test
	public void getAppInfosTest() {
		AppSteamApi gameApi = new AppSteamApi();

		List<SteamApp> steamApps = gameApi.getApps();
		assertFalse(steamApps.isEmpty());
	}

	@Test
	@Ignore
	public void getVersionsTest() {
		AppSteamApi gameApi = new AppSteamApi();

		List<AppVersion> versions =
		gameApi.getVersions(SteamTestUtil.APP_ID);
		assertFalse(versions.isEmpty());
	}
	
	@Test
	public void getHelpfulAppReviewsTest() {
		AppSteamApi appApi = new AppSteamApi();
		
		List<Review> reviews = appApi.getHelpfulAppReviews(SteamTestUtil.APP_ID, 2);
		assertFalse(reviews.isEmpty());
	}
}
