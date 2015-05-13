package org.uta.steam.rest.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.uta.steam.bl.util.PropUtil;

public class SteamTrigger {

	private static Logger LOG = LogManager.getLogger(SteamTrigger.class);
	private Scheduler dataHarvestingSched;
	
	
	public void stopDataHarvesting() {
		if(null != dataHarvestingSched) {
			try {
				dataHarvestingSched.shutdown();				
				dataHarvestingSched = null;
				LOG.info("Data harvesting scheduler stoped");
				
			} catch (SchedulerException e) {
				LOG.error("Error stopping data harvesing scheduler", e);
			}
		}
	}
	

	public void initDataHarvesting() {

		JobDetail emailJob = JobBuilder.newJob(DataHarvestScheduler.class)
				.withIdentity("DataHarvestScheduler", "dataHarvesting").build();

		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("DataHarvestScheduler", "dataHarvesting")
				.withSchedule(CronScheduleBuilder.cronSchedule(
						PropUtil.getProperty("update.cron.expression")))
				.build();

		SchedulerFactory sf = new StdSchedulerFactory();

		try {

			dataHarvestingSched = sf.getScheduler();
			dataHarvestingSched.scheduleJob(emailJob, trigger);
			dataHarvestingSched.start();

			LOG.info("Data harvesting scheduler started");
		} catch (SchedulerException e) {
			LOG.error("Error starting up data harvesing scheduler", e);
		}
	}
}
