package org.uta.steam.bl.test.api;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.uta.steam.bl.api.GameSteamApi;
import org.uta.steam.bl.test.util.SteamTestUtil;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.SteamApp;

public class GameSteamApiTest {


	@Test
	public void getAllAppsTest() {
		GameSteamApi gameApi = new GameSteamApi();
		
		SteamApp[] steamApps = gameApi.getAllApps();
		assertFalse(steamApps.length == 0);
	}

	@Test
	public void getVersionsTest() {
		GameSteamApi gameApi = new GameSteamApi();
		
		List<AppVersion> versions = gameApi.getVersions(SteamTestUtil.APP_ID);
		assertFalse(versions.isEmpty());
	}
}
