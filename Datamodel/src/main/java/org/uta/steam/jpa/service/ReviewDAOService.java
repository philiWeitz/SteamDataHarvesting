package org.uta.steam.jpa.service;

import java.util.Date;
import java.util.List;

import org.uta.steam.jpa.model.Review;

public interface ReviewDAOService extends AbstractDAOService<Review> {

	List<Review> getReviewsByDlcId(long dlcId);
	List<Review> getReviewByAppIdAndVersionId(long appId, Date published);	
}
