package org.uta.steam.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class AppData extends AbstractEntity implements Comparable<AppData> {

	private double price;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> tags;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "APP_REVIEW", referencedColumnName = "id")
	private Set<Review> reviews;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<String> getTags() {
		if (null == tags) {
			tags = new HashSet<String>();
		}
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<Review> getReviews() {
		if (null == reviews) {
			reviews = new HashSet<Review>();
		}
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	// positive = this is larger then o, negative = this is smaller then o
	public int compareTo(AppData o) {
		if(null == getCreated()) {
			return 1;
		}
		if(null == o || null == o.getCreated()) {
			return -1;
		}
		
		return getCreated().compareTo(o.getCreated());
	}
}
