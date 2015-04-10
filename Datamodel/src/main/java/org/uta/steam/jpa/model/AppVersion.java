package org.uta.steam.jpa.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class AppVersion extends AbstractEntity {

	@Basic
	private String title;

	@Basic
	private Date published;
	
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
}
