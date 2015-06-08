package org.uta.steam.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;

@Entity
@SuppressWarnings("serial")
public class SteamApp extends AbstractEntity {

	private long appId;

	private boolean hasData = false;	
	
	private boolean getsUpdated = false;

	@Basic
	private String name = StringUtils.EMPTY;

	@Basic
	@Column(length = 5 * 1024 * 1024)
	private String description = StringUtils.EMPTY;	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID", referencedColumnName = "id")
	private Set<AppData> data;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID", referencedColumnName = "id")
	private Set<AppVersion> versions;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID", referencedColumnName = "id")
	private Set<Review> reviews;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID", referencedColumnName = "id")
	private Set<AppDLC> dlcs;

	
	public long getAppId() {
		return appId;
	}

	public void setAppId(long appid) {
		this.appId = appid;
	}

	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}

	public boolean isGetsUpdated() {
		return getsUpdated;
	}

	public void setGetsUpdated(boolean getsUpdated) {
		this.getsUpdated = getsUpdated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AppData> getData() {
		if (null == data) {
			data = new HashSet<AppData>();
		}
		return data;
	}

	public void setData(Set<AppData> data) {
		this.data = data;
	}

	public Set<AppVersion> getVersions() {
		if (null == versions) {
			versions = new HashSet<AppVersion>();
		}
		return versions;
	}

	public void setVersions(Set<AppVersion> versions) {
		this.versions = versions;
	}
	
	public Set<Review> getReviews() {
		if (null == reviews) {
			reviews = new HashSet<Review>();
		}
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<AppDLC> getDlcs() {
		if (null == dlcs) {
			dlcs = new HashSet<AppDLC>();
		}
		return dlcs;
	}

	public void setDlcs(Set<AppDLC> dlcs) {
		this.dlcs = dlcs;
	}
}
