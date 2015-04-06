package org.uta.steam.bl.ui;

import java.util.Date;

public class UserAchievement {

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
