package org.uta.steam.rest.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.bl.service.SteamDataService;

public class DataHarvestScheduler implements Job {


	@Autowired
	private SteamDataService steamDataService;

	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO: add this if the rest of the server is ready
		//steamDataService.harvestDataFromSteam();
		//steamDataService.updateAppListFromSteam();
	}
}