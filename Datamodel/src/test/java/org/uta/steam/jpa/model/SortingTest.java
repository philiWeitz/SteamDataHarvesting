package org.uta.steam.jpa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.uta.steam.jpa.common.TestUtil;


public class SortingTest {

	@Test
	public void sortingReviewsTest() {
		Date dateNow = new Date();
		
		List<Review> reviews = new LinkedList<Review>();		
		
		Review review = createReview("1",
				DateUtils.addMilliseconds(dateNow, -1), 
				DateUtils.addDays(dateNow, -5), null);
		reviews.add(review);

		review = createReview("3",
				DateUtils.addMilliseconds(dateNow, -2), 
				DateUtils.addDays(dateNow, -1), null);
		reviews.add(review);
		
		review = createReview("2",
				DateUtils.addMilliseconds(dateNow, -1), 
				DateUtils.addDays(dateNow, -4), null);
		reviews.add(review);

		Collections.sort(reviews);
		assertTrue(reviews.get(0).getAuthor().equals("1"));
		assertTrue(reviews.get(1).getAuthor().equals("2"));
		assertTrue(reviews.get(2).getAuthor().equals("3"));
		
		reviews.get(0).setUpdated(dateNow);
		Collections.rotate(reviews, 1);

		Collections.sort(reviews);
		assertTrue(reviews.get(0).getAuthor().equals("2"));
		assertTrue(reviews.get(1).getAuthor().equals("3"));
		assertTrue(reviews.get(2).getAuthor().equals("1"));		
		
		// 2 and 3 having the same post date but 3 was created earlier
		reviews.get(0).setPosted(reviews.get(1).getPosted());
		Collections.rotate(reviews, 1);
		
		Collections.sort(reviews);
		assertTrue(reviews.get(0).getAuthor().equals("3"));
		assertTrue(reviews.get(1).getAuthor().equals("2"));
		assertTrue(reviews.get(2).getAuthor().equals("1"));				
	}
	
	@Test
	public void appDataSortTest() {
		Date dateNow = new Date();
		List<AppData> appDataList = new LinkedList<AppData>();
		
		AppData data = createAppData(2, 
				DateUtils.addDays(dateNow, -2));
		appDataList.add(data);

		data = createAppData(3, 
				DateUtils.addDays(dateNow, -1));
		appDataList.add(data);
		
		data = createAppData(1, 
				DateUtils.addDays(dateNow, -3));
		appDataList.add(data);
		
		Collections.sort(appDataList);
		assertEquals(1, appDataList.get(0).getPrice(), 0.001);
		assertEquals(2, appDataList.get(1).getPrice(), 0.001);		
		assertEquals(3, appDataList.get(2).getPrice(), 0.001);
	}
	
	
	@Test
	public void versionSortTest() {
		Date dateNow = new Date();
		List<AppVersion> versionList = new LinkedList<AppVersion>();
		
		AppVersion version = createVersion("2", 
				DateUtils.addDays(dateNow, -2));
		versionList.add(version);

		version = createVersion("3", 
				DateUtils.addDays(dateNow, -1));
		versionList.add(version);
		
		version = createVersion("1", 
				DateUtils.addDays(dateNow, -3));
		versionList.add(version);
		
		Collections.sort(versionList);
		assertTrue("1".equals(versionList.get(0).getTitle()));
		assertTrue("2".equals(versionList.get(1).getTitle()));		
		assertTrue("3".equals(versionList.get(2).getTitle()));
	}
	
	
	private AppVersion createVersion(String title, Date published) {
		AppVersion result = new AppVersion();
		result.setTitle(title);
		result.setPublished(published);
		
		return result;
	}
		
		
	private AppData createAppData(long price, Date created) {
		AppData result = new AppData();
		result.setPrice(price);
		
		TestUtil.setPrivateField(result, "created", created, AppData.class.getSuperclass());
		
		return result;
	}
	
	
	private Review createReview(String author, Date created, Date posted, Date updated) {
		Review result = new Review();
		result.setAuthor(author);
		result.setPosted(posted);
		result.setUpdated(updated);
		
		TestUtil.setPrivateField(result, "created", created, Review.class.getSuperclass());
		
		return result;
	}
}
