package org.uta.steam.rest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.bl.service.SteamDataService;

public class ServerStartup {

	@Autowired
	private SteamDataService steamDataService;

	public void onServerStartup() {
		// gets a list of all apps available on steam
		steamDataService.updateAppListFromSteam();
	}
}
