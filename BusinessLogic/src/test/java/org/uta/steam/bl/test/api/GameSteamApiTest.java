package org.uta.steam.bl.test.api;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.uta.steam.bl.api.AppSteamApi;
import org.uta.steam.bl.test.util.SteamTestUtil;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.SteamApp;

public class GameSteamApiTest {


	@Test
	public void getAppInfosTest() {
		AppSteamApi gameApi = new AppSteamApi();
		
		SteamApp[] steamApps = gameApi.getApps();
		assertFalse(steamApps.length == 0);
	}

	@Test
	public void getVersionsTest() {
		AppSteamApi gameApi = new AppSteamApi();
		
		List<AppVersion> versions = gameApi.getVersions(SteamTestUtil.APP_ID);
		assertFalse(versions.isEmpty());
	}
}
