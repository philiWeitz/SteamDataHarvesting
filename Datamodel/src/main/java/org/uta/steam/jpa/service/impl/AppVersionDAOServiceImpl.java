package org.uta.steam.jpa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.AppVersionDAOService;

@Service
class AppVersionDAOServiceImpl extends AbstractDAOServiceImpl<AppVersion> implements
	AppVersionDAOService {

	public List<AppVersion> getVersionsByAppId(long appId) {
		List<AppVersion> versions = issueQuery("SELECT versions FROM "
				+ SteamApp.class.getSimpleName() + " a "
				+ "where a.appId = " + appId);
		
		return versions;
	}
}
