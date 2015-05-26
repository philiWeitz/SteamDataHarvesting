package org.uta.steam.jpa.model.service;

import java.util.List;

import org.uta.steam.jpa.model.SteamApp;

public interface SteamAppDAOService extends AbstractDAOService<SteamApp> {
	
	List<SteamApp> getAllAppsAndUpdateList(String searchTerm, int max);
	
	SteamApp getAppByAppIdLazyLoading(long appId);

	SteamApp getWholeAppById(long id);

	SteamApp getWholeAppByAppId(long appId);

	List<SteamApp> getWholeAppsToUpdate();

	boolean addAppToUpdateList(Long appId);
	boolean removeAppFromUpdateList(Long appId);
	
	void updateAppList(List<SteamApp> apps);
	
	List<SteamApp> getAppsWhichHaveData();
}
