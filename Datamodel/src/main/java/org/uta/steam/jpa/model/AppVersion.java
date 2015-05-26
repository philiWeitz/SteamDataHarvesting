package org.uta.steam.jpa.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;

@Entity
@SuppressWarnings("serial")
public class AppVersion extends AbstractEntity implements Comparable<AppVersion> {

	@Basic
	private String title = StringUtils.EMPTY;

	@Basic
	private Date published;

	@Basic
	@Column(length = 5 * 1024 * 1024)
	private String content = StringUtils.EMPTY;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public void setPublished(String published) {
		this.published = new Date(Long.parseLong(published) * 1000);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	// positive = this is larger then o, negative = this is smaller then o
	public int compareTo(AppVersion o) {
		if(null == getPublished()) {
			return 1;
		}
		if(null == o || null == o.getPublished()) {
			return -1;
		}
		
		return getPublished().compareTo(o.getPublished());
	}
}
