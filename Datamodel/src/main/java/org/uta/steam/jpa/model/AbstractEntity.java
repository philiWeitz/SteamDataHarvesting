package org.uta.steam.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(nullable = false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date created;

	public Long getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}
}