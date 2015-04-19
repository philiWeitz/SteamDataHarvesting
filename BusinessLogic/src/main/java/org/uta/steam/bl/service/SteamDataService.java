package org.uta.steam.bl.service;

import java.util.List;

import org.uta.steam.jpa.model.SteamApp;

public interface SteamDataService {
	List<SteamApp> getAllApps();

	boolean addAppToUpdateList(Long appId);
	boolean removeAppFromUpdateList(Long appId);
	
	SteamApp getWholeApp(long appId);

	void harvestDataFromSteam();

	void updateAppListFromSteam();
}
