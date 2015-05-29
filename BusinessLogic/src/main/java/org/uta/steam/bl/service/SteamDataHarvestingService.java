package org.uta.steam.bl.service;

import org.uta.steam.jpa.model.SteamApp;


public interface SteamDataHarvestingService {
	
	void harvestDataFromSteam();
	
	void harvestDataFromSteam(long appId);

	void updateAppListFromSteam();
	
	SteamApp getWholeApp(long appId);
}
