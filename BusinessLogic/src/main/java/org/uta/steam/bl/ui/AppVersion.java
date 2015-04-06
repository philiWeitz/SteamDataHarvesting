package org.uta.steam.bl.ui;

import java.util.Date;

public class AppVersion {

	private String title;
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
