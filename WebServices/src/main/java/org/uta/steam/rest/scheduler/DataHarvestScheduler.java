package org.uta.steam.rest.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.uta.steam.bl.service.SteamDataService;

public class DataHarvestScheduler implements Job {

	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		// gets the spring context
		ApplicationContext springContext = 
			    WebApplicationContextUtils.getWebApplicationContext(
			        ContextLoaderListener.getCurrentWebApplicationContext().getServletContext()
			    );
		
		// gets the service
		SteamDataService steamDataService = springContext.getBean(SteamDataService.class);
		
		System.out.println("Harvesting Data..");	
		steamDataService.harvestDataFromSteam();
		System.out.println("Harvesting Data Done");
		
		//steamDataService.updateAppListFromSteam();
	}
}