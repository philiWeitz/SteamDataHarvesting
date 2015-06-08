package org.uta.steam.rest.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.uta.steam.bl.service.SteamDataHarvestingService;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.rest.scheduler.SteamTrigger;

@WebListener
public class ServletStartupListener implements ServletContextListener {
	
	private static Logger LOG = LogManager.getLogger(ServletStartupListener.class);
	
	
	@Autowired
	private SteamDataService steamDataService;
	@Autowired
	private SteamDataHarvestingService steamDataHarvestingService;
	
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
					
			// start update scheduler
			updateTrigger.initDataHarvesting();	
			
			// get list if only contains debug apps
			if(steamDataService.getAllAppsAndUpdateList(
					StringUtils.EMPTY, 20).size() < 10) {
				
				steamDataHarvestingService.updateAppListFromSteam();
			}		
			
		} catch(Exception e) {
			LOG.error("Error: creating data on server startup!", e);
		}
	}	
}