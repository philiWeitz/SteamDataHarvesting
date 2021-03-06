package org.uta.steam.rest.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.uta.steam.bl.service.SteamDataHarvestingService;

public class DataHarvestScheduler implements Job {

	private static Logger LOG = LogManager.getLogger(DataHarvestScheduler.class);

	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		// gets the spring context
		ApplicationContext springContext = 
			    WebApplicationContextUtils.getWebApplicationContext(
			        ContextLoaderListener.getCurrentWebApplicationContext().getServletContext()
			    );
		
		// gets the service
		SteamDataHarvestingService steamDataHarvestingService = 
				springContext.getBean(SteamDataHarvestingService.class);
		
		LOG.info("Harvesting Data..");	
		steamDataHarvestingService.harvestDataFromSteam();
		LOG.info("Harvesting Data Done");
		
		steamDataHarvestingService.updateAppListFromSteam();
	}
}