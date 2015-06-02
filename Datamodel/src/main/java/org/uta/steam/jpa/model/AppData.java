package org.uta.steam.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
@SuppressWarnings("serial")
public class AppData extends AbstractEntity implements Comparable<AppData> {

	private double price;

	private long positiveReviews;
	
	private long negativeReviews;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> tags;

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getPositiveReviews() {
		return positiveReviews;
	}

	public void setPositiveReviews(long positiveReviews) {
		this.positiveReviews = positiveReviews;
	}

	public long getNegativeReviews() {
		return negativeReviews;
	}

	public void setNegativeReviews(long negativeReviews) {
		this.negativeReviews = negativeReviews;
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
