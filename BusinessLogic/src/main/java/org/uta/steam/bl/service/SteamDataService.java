package org.uta.steam.bl.service;

import java.util.List;

import org.uta.steam.jpa.model.SteamApp;

public interface SteamDataService {
	List<SteamApp> getAllApps();
	void setAppUpdateList(List<Long> appIds);
	void updateAppDataFromSteam();
}
