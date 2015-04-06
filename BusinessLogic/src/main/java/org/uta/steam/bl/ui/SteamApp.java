package org.uta.steam.bl.ui;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class SteamApp {

	private long appid;
	private String name;
	
	@JsonIgnore
	private List<AppVersion> versions;
	
	
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

	public List<AppVersion> getVersions() {
		if(null == versions) {
			versions = new LinkedList<AppVersion>();
		}
		return versions;
	}

	public void setVersions(List<AppVersion> versions) {
		this.versions = versions;
	}
}
