package org.uta.steam.jpa.model;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class AppInfo extends AbstractEntity {

	private long appid;
	
	@Basic
	private String name;
	
	
	public long getAppid() {
		return appid;
	}
	
	public void setAppid(long appid) {
		this.appid = appid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
