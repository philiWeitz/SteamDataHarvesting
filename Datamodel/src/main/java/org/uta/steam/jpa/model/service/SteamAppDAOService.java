package org.uta.steam.jpa.model.service;

import java.util.List;

import org.uta.steam.jpa.model.SteamApp;

public interface SteamAppDAOService extends AbstractDAOService<SteamApp> {

	SteamApp getAppByAppIdLazyLoading(long appId);

	SteamApp getWholeAppById(long id);

	SteamApp getWholeAppByAppId(long appId);

	List<SteamApp> getWholeAppsToUpdate();

	void setAppUpdateList(List<Long> appIds);

	void updateAppList(List<SteamApp> apps);
}
