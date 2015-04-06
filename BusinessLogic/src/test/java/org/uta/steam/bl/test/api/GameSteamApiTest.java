package org.uta.steam.bl.test.api;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.uta.steam.bl.api.GameSteamApi;
import org.uta.steam.bl.ui.AppVersion;
import org.uta.steam.bl.ui.SteamApp;

public class GameSteamApiTest {

	private static final long APP_ID = 282070;
	
	
	@Test
	public void getAllAppsTest() {
		GameSteamApi gameApi = new GameSteamApi();
		
		SteamApp[] steamApps = gameApi.getAllApps();
		assertFalse(steamApps.length == 0);
	}

	@Test
	public void getVersionsTest() {
		GameSteamApi gameApi = new GameSteamApi();
		
		List<AppVersion> versions = gameApi.getVersions(APP_ID);
		assertFalse(versions.isEmpty());
	}
}
