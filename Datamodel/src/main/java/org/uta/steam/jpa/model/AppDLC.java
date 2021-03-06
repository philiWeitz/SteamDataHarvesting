package org.uta.steam.jpa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@Entity
@SuppressWarnings("serial")
public class AppDLC extends AbstractEntity {

	private long dlcId;

	@Basic
	private String name = StringUtils.EMPTY;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DLC_RELEASE_DATE")
	private Date releaseDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "DLC_DATA", referencedColumnName = "id")
	private Set<AppData> data;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DLC_REVIEW", referencedColumnName = "id")
	private Set<Review> reviews;
	
	public long getDlcId() {
		return dlcId;
	}

	public void setDlcId(long dlcId) {
		this.dlcId = dlcId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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
	
	public Set<Review> getReviews() {
		if (null == reviews) {
			reviews = new HashSet<Review>();
		}
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
}
