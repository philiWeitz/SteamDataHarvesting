package org.uta.steam.jpa.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.service.ReviewDAOService;

@Service
class ReviewDAOServiceImpl extends AbstractDAOServiceImpl<Review> implements
	ReviewDAOService {

	
	public List<Review> getReviewsByDlcId(long dlcId) {
		return issueQuery("SELECT d.reviews FROM "
				+ AppDLC.class.getSimpleName() + " d "
				+ "where d.dlcId = " + dlcId);		
	}	
	

	// TODO: select versions by database query
	public List<Review> getReviewByAppIdAndVersionId(long appId, Date published) {
		List<Review> reviews = issueQuery("SELECT a.reviews FROM "
				+ SteamApp.class.getSimpleName() + " a "
				+ "where a.appId = " + appId);
		
		if(published == null) {
			return getReviewsWithoutVersion(reviews);
		} else {
			return getReviewsBasedOnPublishedDate(reviews, published);
		}
	}
	
	
	private List<Review> getReviewsWithoutVersion(List<Review> reviews) {
		List<Review> result = new LinkedList<Review>();
		
		for(Review review : reviews) {
			if(null == review.getVersion()) {
				result.add(review);
			}
		}
		
		return result;
	}

	
	private List<Review> getReviewsBasedOnPublishedDate(List<Review> reviews, Date published) {
		List<Review> result = new LinkedList<Review>();
		
		for(Review review : reviews) {
			if(null != review.getVersion() && 
					review.getVersion().getPublished().compareTo(published) == 0) {
				
				result.add(review);
			}
		}
		
		return result;
	}
}
