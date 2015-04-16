package org.uta.steam.jpa.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;


@Resource
class SteamAppDAOServiceImpl 
	extends AbstractDAOServiceImpl<SteamApp> 
	implements SteamAppDAOService {

	
	private static Logger LOG = LogManager
			.getLogger(SteamAppDAOServiceImpl.class);
	
	
	public SteamApp getWholeAppById(long id) {
		SteamApp result = null; 
		
		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			result = em.find(SteamApp.class, id);
			result.getData();
			result.getDlcs();
			result.getVersions();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while getting \"" + result.getClass() + "\" (id: "
					+ id + ")", e);
			
		} finally {
			em.close();
		}
		
		return result;
	}
	
	
	public void addAppToUpdateList(long appId) {
		SteamApp app = getAppByAppId(appId);
		app.setGetsUpdated(true);

		saveOrUpdate(app);
	}
	
	
	// gets all the application data
	public List<SteamApp> getWholeAppsToUpdate() {
		List<SteamApp> apps = issueQuery(
				"SELECT a FROM " + SteamApp.class.getSimpleName() + " a "
						+ "where a.getsUpdated = true");
		
		List<SteamApp> result = new LinkedList<SteamApp>();
		for(SteamApp app : apps) {
			result.add(getWholeAppById(app.getId()));
		}
				
		return result;
	}
	
	
	public SteamApp getAppByAppId(long appId) {
		SteamApp result = null;
		
		List<SteamApp> apps = issueQuery(
				"SELECT a FROM " + SteamApp.class.getSimpleName() + " a "
						+ "where a.appId = " + appId);
		
		if(!apps.isEmpty()) {
			result = apps.get(0);
		}

		return result;
	}
}
