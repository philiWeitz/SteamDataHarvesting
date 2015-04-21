package org.uta.steam.rest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.SteamApp;

public class ServerStartup {

	@Autowired
	private SteamDataService steamDataService;

	public void onServerStartup() {
		// gets a list of all apps available on steam
		steamDataService.updateAppListFromSteam();
		
		// TODO: only for debug purpose - remove this
		SteamApp app = steamDataService.getWholeApp(226840);
		
		if(null != app && app.getData().isEmpty()) {
			steamDataService.addAppToUpdateList(226840l);
			steamDataService.addAppToUpdateList(282070l);
			steamDataService.harvestDataFromSteam();
			steamDataService.harvestDataFromSteam();	
		}		
	}
}
