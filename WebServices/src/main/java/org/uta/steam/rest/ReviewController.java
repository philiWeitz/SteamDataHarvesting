package org.uta.steam.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.Review;


@RestController
@RequestMapping("/service/review")
public class ReviewController {

	/*
	 * example call: -
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/review/getReviewsByAppIdAndVersion/226840/1429022761000
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/review/getReviewsWithoutVersionByAppId/226840
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/review/getReviewsByDlcId/329530
	 */

	private static Logger LOG = LogManager.getLogger(ReviewController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private SteamDataService steamDataService;


	@RequestMapping(value = "/getReviewsByDlcId/{dlcId}", method = RequestMethod.GET)
	public ResponseEntity<String> getReviewsByDlcId(@PathVariable long dlcId) {
		String jsonString = StringUtils.EMPTY;

		try {
			List<Review> reviews = 
					steamDataService.getReviewsByDlcId(dlcId);
			
			// sort the reviews based on the posted date
			Collections.sort(reviews);

			jsonString = mapper.writeValueAsString(reviews);		
			return new ResponseEntity<String>(jsonString, HttpStatus.OK);
			
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}

		return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
	}	
			
	@RequestMapping(value = "/getReviewsByAppIdAndVersion/{appId}/{published}", method = RequestMethod.GET)
	public ResponseEntity<String> getReviewsByAppIdAndVersion(@PathVariable long appId, @PathVariable long published) {
		return getReviews(appId, new Date(published));
	}
	
	@RequestMapping(value = "/getReviewsWithoutVersionByAppId/{appId}", method = RequestMethod.GET)
	public ResponseEntity<String> getReviewsWithoutVersionByAppId(@PathVariable long appId) {
		return getReviews(appId, null);
	}
	
	// version can be null
	private ResponseEntity<String> getReviews(long appId, Date published) {
		String jsonString = StringUtils.EMPTY;

		try {
			List<Review> reviews = 
					steamDataService.getReviewByAppIdAndVersionId(appId, published);
			
			// sort the reviews based on the posted date
			Collections.sort(reviews);
			
			// remove version from reviews to increase speed
			for(Review review : reviews) {
				review.setVersion(null);
			}
			
			jsonString = mapper.writeValueAsString(reviews);		
			return new ResponseEntity<String>(jsonString, HttpStatus.OK);
			
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}

		return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
	}
}