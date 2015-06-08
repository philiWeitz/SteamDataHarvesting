package org.uta.steam.jpa.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.service.AppDlcDAOService;

@Service
class dlcDAOServiceImpl extends AbstractDAOServiceImpl<AppDLC> implements
	AppDlcDAOService {

	public List<AppDLC> getDlcsByAppId(long appId) {
		List<AppDLC> result = issueQuery("SELECT dlcs FROM "
				+ SteamApp.class.getSimpleName() + " a "
				+ "where a.appId = " + appId);
		
		// remove the lazy loaded set
		for(AppDLC dlc : result) {
			dlc.setReviews(Collections.<Review> emptySet());
		}
		
		return result;
	}
}
