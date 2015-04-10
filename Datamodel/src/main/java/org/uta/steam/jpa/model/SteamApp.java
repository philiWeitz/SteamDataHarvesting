package org.uta.steam.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class SteamApp extends AbstractEntity {

	private long appid;
	
	private double price;
	
	@Basic
	private String name;
		
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="APP_VERSION", referencedColumnName="id")
	private Set<AppVersion> versions;

	@ElementCollection(fetch=FetchType.EAGER)
	private Set<String> dlcs;	

	@ElementCollection(fetch=FetchType.EAGER)
	private Set<String> tags;		
		
	
	public long getAppid() {
		return appid;
	}
	
	public void setAppid(long appid) {
		this.appid = appid;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
	

	public Set<String> getDlcs() {
		if(null == dlcs) {
			dlcs = new HashSet<String>();
		}
		return dlcs;
	}

	public void setDlcs(Set<String> dlcs) {
		this.dlcs = dlcs;
	}

	public Set<String> getTags() {
		if(null == tags) {
			tags = new HashSet<String>();
		}
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
