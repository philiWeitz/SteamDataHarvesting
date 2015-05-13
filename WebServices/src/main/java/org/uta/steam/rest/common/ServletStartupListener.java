package org.uta.steam.rest.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.bl.util.PropUtil;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.rest.scheduler.SteamTrigger;

@WebListener
public class ServletStartupListener implements ServletContextListener {
	
	private static Logger LOG = LogManager.getLogger(ServletStartupListener.class);
	
	
	@Autowired
	private SteamDataService steamDataService;
	private SteamTrigger updateTrigger = new SteamTrigger();
	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		updateTrigger.stopDataHarvesting();
	}

	public void contextInitialized(ServletContextEvent ctx) {		
		
		try {

			WebApplicationContextUtils
        	.getRequiredWebApplicationContext(ctx.getServletContext())
        	.getAutowireCapableBeanFactory()
        	.autowireBean(this);
			
			// get list if only contains debug apps - otherwise update once per week
			if(steamDataService.getAllAppsAndUpdateList(
					StringUtils.EMPTY, 20).size() < 10) {
				
				steamDataService.updateAppListFromSteam();
				
				// create an example steam app
				if(PropUtil.getPropertyAsBoolean("debug.active")) {
					createExampleApp();
				}
			}
			
			// start update scheduler
			updateTrigger.initDataHarvesting();			
			
		} catch(Exception e) {
			LOG.error("Error: creating data on server startup!", e);
		}
	}	
		
	
	// only for debug purpose
	private void createExampleApp() {
		SteamApp app = steamDataService.getWholeApp(226840);
		
		if(null != app && app.getData().isEmpty()) {
			steamDataService.addAppToUpdateList(226840l);
			steamDataService.harvestDataFromSteam();
		}
	}
}