package org.uta.steam.jpa.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	
	
	public SteamApp getAppByAppIdLazyLoading(long appId) {
		SteamApp result = issueQuerySingleResult(
				"SELECT a FROM " + SteamApp.class.getSimpleName() + " a "
						+ "where a.appId = " + appId);
		
		return result;
	}
	
	
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
	
	
	public SteamApp getWholeAppByAppId(long appId) {
		SteamApp result = null;
		
		result = getAppByAppIdLazyLoading(appId);
		
		if(null != result) {
			result = getWholeAppById(result.getId());
		}
		
		return result;
	}
	

	public void setAppUpdateList(List<Long> appIds) {		
		List<SteamApp> toUpdate = new LinkedList<SteamApp>();
		
		List<SteamApp> apps = issueQuery(
				"SELECT a FROM " + SteamApp.class.getSimpleName() + " a "
						+ "where a.getsUpdated = true");
		
		List<Long> appIdsToAdd = new LinkedList<Long>(appIds);
		
		// deselect the old ones
		for(SteamApp app : apps) {
			if(!appIds.contains(app.getAppId())) {
				app.setGetsUpdated(false);
				toUpdate.add(app);
			} else {
				appIdsToAdd.remove(app.getAppId());
			}
		}
		
		// add the new ones
		for(long appId : appIdsToAdd) {
			SteamApp app = getAppByAppIdLazyLoading(appId);
			app.setGetsUpdated(true);
			toUpdate.add(app);
		}
		
		saveOrUpdateAll(toUpdate);
	}


	public void updateAppList(List<SteamApp> apps) {
		
		List<Long> appIdsFromDb = issueQuery(
				"SELECT a.appId FROM " + SteamApp.class.getSimpleName() + " a ");
	
		Set<Long> appIdSet = new HashSet<Long>(appIdsFromDb);
		
		List<SteamApp> toUpdate = new LinkedList<SteamApp>();
		for(SteamApp app : apps) {
			if(!appIdSet.contains(app.getAppId())) {
				toUpdate.add(app);
			}
		}
		
		saveOrUpdateAll(toUpdate);
	}
}
