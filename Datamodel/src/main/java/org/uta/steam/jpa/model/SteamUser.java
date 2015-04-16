package org.uta.steam.jpa.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@SuppressWarnings("serial")
public class SteamUser extends AbstractEntity {

	@Basic
	private String userId;
	
	@Basic
	private String userName;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="USER_APPS", referencedColumnName="id")
	private Set<UserApp> apps;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ACHIEVEMENT_SUM", referencedColumnName="id")
	private Set<UserAchievementSummary> achievements;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Set<UserApp> getApps() {
		if(null == apps) {
			apps = new HashSet<UserApp>();
		}
		return apps;
	}
	
	public void setApps(Set<UserApp> apps) {
		this.apps = apps;
	}

	public void setApps(List<UserApp> apps) {
		if(null != apps) {
			this.apps = new HashSet<UserApp>(apps);
		}
	}
	
	public Set<UserAchievementSummary> getAchievements() {
		if(null == achievements) {
			achievements = new HashSet<UserAchievementSummary>();
		}
		return achievements;
	}
	
	public void setAchievements(Set<UserAchievementSummary> achievements) {
		this.achievements = achievements;
	}
}
