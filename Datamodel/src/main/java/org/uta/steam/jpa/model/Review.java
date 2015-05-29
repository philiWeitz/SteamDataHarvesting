package org.uta.steam.jpa.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@Entity
@SuppressWarnings("serial")
public class Review extends AbstractEntity  implements Comparable<Review> {

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

	private boolean recommended = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POSTED_DATE", nullable = false)
	private Date posted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updated;

	@ManyToOne(optional=true)
	@JoinColumn(name="VERSION_ID")
	private AppVersion version;
	
	
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

	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
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
	
	public AppVersion getVersion() {
		return version;
	}

	public void setVersion(AppVersion version) {
		this.version = version;
	}
	
	@Override
	public int hashCode() {
		try {
			String hashString = author + content + peopleSeen + 
					peopleHelpful + playTimeLast2Weeks + playTimeAll;
			
			if(null != posted) {
				hashString += posted.getTime();
			} 
			if(null != updated) {
				hashString += updated.getTime();
			}

			return hashString.hashCode();
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int compareTo(Review o) {
		Date c1 = getPosted();
		Date c2 = o.getPosted();
		
		if(null != updated) {
			c1 = getUpdated();
		}
		if(null != o.getUpdated()) {
			c2 = o.getUpdated();
		}
		
		if(null == c1) {
			return 1;
		} else if(null == c2) {
			return -1;
		} else if(c1.compareTo(c2) == 0) {
			return getCreated().compareTo(o.getCreated());
		} else {
			return c1.compareTo(c2);
		}
	}	
}
