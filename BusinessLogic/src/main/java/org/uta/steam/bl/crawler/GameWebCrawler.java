package org.uta.steam.bl.crawler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uta.steam.bl.ui.Review;


public class GameWebCrawler extends AbstractWebCrawler {

	private static Logger LOG = LogManager.getLogger(GameWebCrawler.class);
	
	private static final String BASE_URL = "http://store.steampowered.com/app/";
	
	private long appId = -1;
	private Document htmlDoc = null;

	
	public List<String> getDLCsFromHtmlString(long appId) {
		Document doc = getHtmlFromUrl(appId);
		Elements elements = getElementsByClass(doc, "game_area_dlc_name");
		
		return getInnerHtml(elements);
	}
	
	
	public double getAppPrice(long appId) {
		double result = 0;
	
		Document doc = getHtmlFromUrl(appId);
		Elements elements = getElementsByClass(doc, "game_purchase_price");
		
		List<String> prices = getInnerHtml(elements);
		
		if(!prices.isEmpty()) {
			try {
				result = Double.parseDouble(prices.get(0)
						.replace(',', '.')
						.replaceAll("€$", ""));
				
			} catch (Exception e) {
				LOG.error("Error converting price string to double", e);
			}
		}
		
		return result;
	}
	
	
	public List<String> getAppTags(long appId) {
		Document doc = getHtmlFromUrl(appId);
		Elements elements = getElementsByClass(doc, "app_tag");
		
		List<String> result = getInnerHtml(elements);
		
		if(!result.isEmpty()) {
			// last element is the extention button (+)
			result.remove(result.size() -1);
		}
		
		return result;
	}
	
	
	public List<Review> getHelpfulUserReviews(long appId) {
		Document doc = getHtmlFromUrl(appId);		
		Element helpfulReviews = getElementById(doc, "Reviews_all");	

		return extractReviewData(helpfulReviews);
	}
	
	
	private List<Review> extractReviewData(Element element) {
		List<Review> result = new LinkedList<Review>();

		if(null != element) {
			Elements reviews = getElementsByClass(element, "review_box");			
			
			Iterator<Element> iterator = reviews.iterator();
			while (iterator.hasNext()) {
				Element reviewElement = iterator.next();
				
				Review review = new Review();
				review.setAuthor(reviewElement.getElementsByClass("persona_name").text());
				review.setContent(reviewElement.getElementsByClass("content").text());
				result.add(review);
			}
		}
		
		return result;
	}

	
	private Document getHtmlFromUrl(long appId) {

		if(this.appId != appId) {
			
			this.appId = appId;
			htmlDoc = super.getHtmlFromUrl(BASE_URL + appId);
		}
		
		return htmlDoc;
	}
}
