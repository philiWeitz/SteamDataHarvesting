package org.uta.steam.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
public class SteamApp extends AbstractEntity {

	private long appid;
	
	@Basic
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="APP_VERSION", referencedColumnName="id")
	private Set<AppVersion> versions;

	
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

	public Set<AppVersion> getVersions() {
		if(null == versions) {
			versions = new HashSet<AppVersion>();
		}
		return versions;
	}

	public void setVersions(Set<AppVersion> versions) {
		this.versions = versions;
	}
}
