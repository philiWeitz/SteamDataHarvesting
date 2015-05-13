package org.uta.steam.jpa.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@Entity
@SuppressWarnings("serial")
public class Review extends AbstractEntity {

	@Basic
	private String author = StringUtils.EMPTY;

	private String authorSteamId = StringUtils.EMPTY;

	@Basic
	@Column(length = 5 * 1024 * 1024)
	private String content = StringUtils.EMPTY;

	private double playTimeAll;	
	
	private double playTimeLast2Weeks;
	
	private long peopleSeen;
	
	private long peopleHelpful;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POSTED_DATE")
	private Date posted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updated;

	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorSteamId() {
		return authorSteamId;
	}

	public void setAuthorSteamId(String authorSteamId) {
		this.authorSteamId = authorSteamId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getPlayTimeAll() {
		return playTimeAll;
	}

	public void setPlayTimeAll(double playTimeAll) {
		this.playTimeAll = playTimeAll;
	}

	public double getPlayTimeLast2Weeks() {
		return playTimeLast2Weeks;
	}

	public void setPlayTimeLast2Weeks(double playTimeLast2Weeks) {
		this.playTimeLast2Weeks = playTimeLast2Weeks;
	}

	public long getPeopleSeen() {
		return peopleSeen;
	}

	public void setPeopleSeen(long peopleSeen) {
		this.peopleSeen = peopleSeen;
	}

	public long getPeopleHelpful() {
		return peopleHelpful;
	}

	public void setPeopleHelpful(long peopleHelpful) {
		this.peopleHelpful = peopleHelpful;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}	
}
