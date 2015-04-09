package org.uta.steam.jpa.model;

import javax.persistence.Basic;

@SuppressWarnings("serial")
public class Review extends AbstractEntity {

	@Basic
	private String author;
	
	@Basic
	private String content;
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
