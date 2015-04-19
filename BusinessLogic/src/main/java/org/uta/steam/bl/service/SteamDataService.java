package org.uta.steam.bl.service;

import java.util.List;

import org.uta.steam.jpa.model.SteamApp;

public interface SteamDataService {
	List<SteamApp> getAllApps();

	void addAppToUpdateList(Long appId);
	void removeAppFromUpdateList(Long appId);
	
	SteamApp getWholeApp(long appId);

	void harvestDataFromSteam();

	void updateAppListFromSteam();
}
