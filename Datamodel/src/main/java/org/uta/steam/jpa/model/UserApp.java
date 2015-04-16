package org.uta.steam.jpa.model;




@SuppressWarnings("serial")
public class UserApp extends AbstractEntity {

	private long appid;
	private long playtime;
	
	
	public long getAppid() {
		return appid;
	}
	
	public void setAppid(long appid) {
		this.appid = appid;
	}
	
	public void setAppid(String appid) {
		this.appid = Long.parseLong(appid);
	}	
	
	public long getPlaytime() {
		return playtime;
	}
	
	public void setPlaytime(long playtime) {
		this.playtime = playtime;
	}
	
	public void setPlaytime(String playtime) {
		this.playtime = Long.parseLong(playtime);
	}	
}
