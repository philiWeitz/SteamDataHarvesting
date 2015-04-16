package org.uta.steam.jpa.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SuppressWarnings("serial")
public class Review extends AbstractEntity {

	private long authorId;
	
	@Basic
	private String author;	
	
	@Basic
	@Column(length = 5 * 1024 * 1024)
	private String content;
	
	private long peopleSeen;
	private long peopleHelpful;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POSTED_DATE")
	private Date posted;
	
		
	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

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
}
