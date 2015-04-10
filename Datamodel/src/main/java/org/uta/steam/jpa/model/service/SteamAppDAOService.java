package org.uta.steam.jpa.model.service;

import java.util.List;

import org.uta.steam.jpa.model.AppInfo;
import org.uta.steam.jpa.model.SteamApp;

public interface SteamAppDAOService extends AbstractDAOService<SteamApp> {
	
	void updateAppInfos(List<AppInfo> infos);
	String getAppNameByAppId(long appId);
}
