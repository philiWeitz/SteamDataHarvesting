package org.uta.steam.rest.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronExpression;
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
	private SchedulerFactory sf;
	
	
	public SteamTrigger() {
		sf = new StdSchedulerFactory();	
	}
	
	
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
		String cronJobString = PropUtil.getProperty("update.cron.expression");
		
		if(CronExpression.isValidExpression(cronJobString)) {

			JobDetail updateJob = JobBuilder.newJob(DataHarvestScheduler.class)
					.withIdentity("DataHarvestScheduler", "dataHarvesting")
					.storeDurably(true)
					.requestRecovery(true)
					.build();
			
			CronTrigger trigger = TriggerBuilder.newTrigger()
					.forJob(updateJob)
					.withPriority(10)
					.withSchedule(CronScheduleBuilder.cronSchedule(cronJobString))
					.build();
	
			try {
	
				dataHarvestingSched = sf.getScheduler();
				dataHarvestingSched.scheduleJob(updateJob, trigger);
				dataHarvestingSched.start();
	
				LOG.info("Data harvesting scheduler started");
			} catch (SchedulerException e) {
				LOG.error("Error starting up data harvesing scheduler", e);
			}
		} else {
			LOG.error("Error: Invalid cron job defined (" + cronJobString + "");
		}
	}
}
