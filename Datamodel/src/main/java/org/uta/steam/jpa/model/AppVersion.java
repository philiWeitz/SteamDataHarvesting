package org.uta.steam.jpa.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;

@Entity
@SuppressWarnings("serial")
public class AppVersion extends AbstractEntity {

	@Basic
	private String title = StringUtils.EMPTY;

	@Basic
	private Date published;

	@Basic
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
}
