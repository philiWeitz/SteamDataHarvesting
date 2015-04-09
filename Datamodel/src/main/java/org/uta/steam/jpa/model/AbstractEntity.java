package org.uta.steam.jpa.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass @SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}
}