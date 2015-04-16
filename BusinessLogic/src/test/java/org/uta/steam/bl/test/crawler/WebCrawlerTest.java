package org.uta.steam.bl.test.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.uta.steam.bl.crawler.AppWebCrawler;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.Review;

public class WebCrawlerTest {

	private static final long APP_ID = 282070;
	
	@Test
	public void gettingDLCsListTest() {
		AppWebCrawler gameWebCrawler = new AppWebCrawler();
		
		// get list of DLCs
		List<AppDLC> listOfDLCs = gameWebCrawler.getDLCs(APP_ID);
		assertFalse(listOfDLCs.isEmpty());	
	}
	
	
	@Test
	public void getAppPriceTest() {
		AppWebCrawler gameWebCrawler = new AppWebCrawler();
		
		// get price
		double price = gameWebCrawler.getAppPrice(APP_ID);
		assertEquals(18.99, price, 0.001);	
	}
	
	
	@Test
	public void getAppTagsTest() {
		AppWebCrawler gameWebCrawler = new AppWebCrawler();
		
		// get tags
		List<String> tags = gameWebCrawler.getAppTags(APP_ID);
		assertFalse(tags.isEmpty());	
		assertEquals(20, tags.size());
	}
	
	
	@Test
	public void getHelpfulUserReviewsTest() {
		AppWebCrawler gameWebCrawler = new AppWebCrawler();
		
		// get reviews
		List<Review> reviews = gameWebCrawler.getHelpfulUserReviews(APP_ID);
		assertFalse(reviews.isEmpty());
	}
}
