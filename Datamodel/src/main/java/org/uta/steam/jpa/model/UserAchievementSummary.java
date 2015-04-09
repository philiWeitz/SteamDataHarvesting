package org.uta.steam.jpa.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class UserAchievementSummary extends AbstractEntity {

	private Date timestamp;
	
	private long appId;
	private long achieved;
	private long maximumAchievements;
	
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
		
	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public long getAchieved() {
		return achieved;
	}
	
	public void setAchieved(long achieved) {
		this.achieved = achieved;
	}
	
	public long getMaximumAchievements() {
		return maximumAchievements;
	}
	
	public void setMaximumAchievements(long maximumAchievements) {
		this.maximumAchievements = maximumAchievements;
	}
}
