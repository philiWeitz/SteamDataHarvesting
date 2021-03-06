package org.uta.steam.bl.api;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uta.steam.bl.util.PropUtil;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.Review;

class ReviewSteamApi extends AbstractSteamApi {
		
	private static Logger LOG = LogManager.getLogger(ReviewSteamApi.class);

	private static final String RECOMMENDED_KEY = "recommended";
	
	private DateFormat formatWithYear = new SimpleDateFormat(
			PropUtil.getProperty("posted.date.format.with.year"), Locale.ENGLISH);
	private DateFormat formatNoYear = new SimpleDateFormat(
			PropUtil.getProperty("posted.date.format.no.year"), Locale.ENGLISH);
	
	
	
	public List<Review> getHelpfulPositiveAppReviews(long appId) {		
		return getAppREviews(appId, "steam.get.app.review.positive.url", "positive");
	}

	
	public List<Review> getHelpfulNegativeAppReviews(long appId) {		
		return getAppREviews(appId, "steam.get.app.review.negative.url", "negative");
	}

	
	public List<Review> getRecentAppReviews(long appId) {		
		return getAppREviews(appId, "steam.get.app.review.recent.url", "recent");
	}
	
	
	private List<Review> getAppREviews(long appId, String urlKey, String listName) {

		// we can only retrieve reviews in chunks of 20
		int lastResponseHash = 0;
		int amount = PropUtil.getPropertyAsInteger("app.reviews.to.collect");
		
		List<Review> result = new LinkedList<Review>();				
		for(int offset = 0; offset < amount; offset += 20) {
					
			String jsonResponse = httpGet(PropUtil.getProperty(urlKey, appId, offset));
			
			// check if the response is the same as the last one
			int newHash = jsonResponse.hashCode();
			// is equal -> no more reviews available
			if(newHash == lastResponseHash) {
				break;
			} else {
				lastResponseHash = newHash;
			}
			
			try {

				// HTML is inside a JSON string
				JsonNode rootNode = new ObjectMapper().readTree(new StringReader(jsonResponse));
				String xmlResponse = rootNode.get("html").asText();

				// convert HTML into review items
				Document doc = Jsoup.parse(xmlResponse);
				Elements linkToReviews = doc.getElementsByClass("title");

				Iterator<Element> iter = linkToReviews.iterator();
				while (iter.hasNext()) {
					Element link = iter.next();					
					Review review = new Review();
					
					String url = link.getElementsByTag("a").first().attr("href");
					Document reviewDoc = Jsoup.connect(url).get();
					
					review.setListName(listName);
					review = setGeneralInformation(reviewDoc, review);
					review = setPostedAndUpdatedDate(reviewDoc, review);
					review = setHoursPlayed(reviewDoc, review);
					review = setUsersSeenVsHelpful(reviewDoc, review);
					review = setIsRecommended(reviewDoc, review);
					
					result.add(review);
				}
				
			} catch (JsonProcessingException e) {
				LOG.error(e);
			} catch (IOException e) {
				LOG.error(e);
			} catch (NullPointerException e) {
				LOG.error("Getting all reviews page is not a valid HTML page! (" 
					+ PropUtil.getProperty(urlKey, appId, offset) + ")");
			}
		}
		
		return result;
	}
	
	
	private Review setGeneralInformation(Document doc, Review review) {
		review.setAuthor(doc.getElementsByClass("profile_small_header_name").text());
		review.setContent(doc.getElementsByClass("review_area_content").text());
		
		// get user steam id
		Elements idElements = doc.getElementsByClass("commentthread_area");
		if(!idElements.isEmpty()) {
						
			Matcher matcher = SteamUtil.NUMBER_PATTERN_INT.matcher(idElements.first().id());
			
			if(matcher.find()) {
				review.setAuthorSteamId(matcher.group());
			}
		}
		
		return review;
	}
	
	
	private Review setPostedAndUpdatedDate(Document doc, Review review) {
		String item = doc.getElementsByClass("recommendation_date").text()
				.replace(" @", StringUtils.EMPTY);	
		
		String[] contents = item.split("Updated: ");
		review.setPosted(getDate(contents[0].replace("Posted: ", StringUtils.EMPTY)));
		
		if(contents.length > 1) {
			review.setUpdated(getDate(contents[1]));
		}
		
		return review;
	}
	
	
	private Review setHoursPlayed(Document doc, Review review) {
		String playedText = doc.getElementsByClass("playTime").text()
				.replace(",", StringUtils.EMPTY);
		
		Matcher matcher = SteamUtil.NUMBER_PATTERN_DOUBLE.matcher(playedText);
		if(matcher.find()) {
		
			try {
				review.setPlayTimeLast2Weeks(Double.parseDouble(matcher.group()));
				
				if(matcher.find()) {
					review.setPlayTimeAll(Double.parseDouble(matcher.group()));				
				}
			} catch(NumberFormatException e) {
				LOG.error("Error: Couldn't parse play time string (" + playedText + ")");
			}
		}
		
		return review;
	}
	

	private Review setUsersSeenVsHelpful(Document doc, Review review) {
		String ratingBar = doc.getElementsByClass("ratingBar").text()
				.replace(",", StringUtils.EMPTY);
	
		Matcher matcher = SteamUtil.NUMBER_PATTERN_INT.matcher(ratingBar);
		if(matcher.find()) {
			
			try {
				review.setPeopleHelpful(Long.parseLong(matcher.group()));
				
				if(matcher.find()) {
					review.setPeopleSeen(Long.parseLong(matcher.group()));				
				}
			} catch(NumberFormatException e) {
				LOG.error("Error: Couldn't parse rating bar string (" + ratingBar + ")");
			}
		}
		
		return review;
	}
	
	
	private Review setIsRecommended(Document doc, Review review) {
		String recommended = doc.getElementsByClass("ratingSummary").text().toLowerCase();
		
		if(RECOMMENDED_KEY.equals(recommended)) {
			review.setRecommended(true);
		} else {
			review.setRecommended(false);
		}
		
		return review;
	}
	
	
	private Date getDate(String dateString) {
		try {
			return formatWithYear.parse(dateString);
		} catch (ParseException e) {
			// try without year
		}
		
		try {
			Date result = formatNoYear.parse(dateString);
			
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			
			c.setTime(result);
			c.set(Calendar.YEAR, year);

			return c.getTime();
			
		} catch (ParseException e) {
			LOG.error("Error: Invalid review post date (" + dateString + ")!");
		}
		
		return null;
	}
}
