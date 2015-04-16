package org.uta.steam.jpa.model.service;

import java.util.List;

import org.uta.steam.jpa.model.SteamApp;

public interface SteamAppDAOService extends AbstractDAOService<SteamApp> {
	
	SteamApp getWholeAppById(long id);
	SteamApp getAppByAppId(long appId);
	
	void addAppToUpdateList(long appId);
	List<SteamApp> getWholeAppsToUpdate();	
}
