package org.uta.steam.bl.api;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;

public class AppSteamApi extends AbstractSteamApi {

	private static final Pattern VERSION_PATTERN = Pattern
			.compile("(version ([0-9].)+[0-9]+)|(update)");

	private static final Pattern NUMBER_PATTERN = Pattern
			.compile("[0-9][0-9]* ");
	
	private static Logger LOG = LogManager.getLogger(AppSteamApi.class);
	
	
	public List<SteamApp> getApps() {

		List<SteamApp> result = new LinkedList<SteamApp>();

		String jsonResponse = httpGet("http://api.steampowered.com/ISteamApps/GetAppList/v0001/?format=xml");

		Document doc = Jsoup.parse(jsonResponse);
		Elements appItems = doc.getElementsByTag("app");

		Iterator<Element> iter = appItems.iterator();
		while (iter.hasNext()) {
			Element appItem = iter.next();

			SteamApp app = new SteamApp();
			app.setName(appItem.getElementsByTag("name").text());
			app.setAppId(Long.parseLong(appItem.getElementsByTag("appid")
					.text()));
			result.add(app);
		}

		return result;
	}

	public List<AppVersion> getVersions(long appId) {
		List<AppVersion> result = new LinkedList<AppVersion>();

		String xmlResponse = httpGet("https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?format=xml&maxlength=1&"
				+ "key=" + SteamUtil.API_KEY + "&appid=" + appId);

		Document doc = Jsoup.parse(xmlResponse);
		Elements newsItems = doc.getElementsByTag("newsitem");

		Iterator<Element> iter = newsItems.iterator();
		while (iter.hasNext()) {
			Element newsitem = iter.next();
			Element title = newsitem.getElementsByTag("title").first();

			Matcher matcher = VERSION_PATTERN.matcher(title.text()
					.toLowerCase());
			if (matcher.find()) {
				Element published = newsitem.getElementsByTag("date").first();

				AppVersion version = new AppVersion();
				version.setTitle(title.text());
				version.setPublished(published.text());
				result.add(version);
			}
		}

		return result;
	}
	
	
	public List<Review> getHelpfulAppReviews(long appId, int rounds) {		
		List<Review> result = new LinkedList<Review>();
		
		int firstItemHash = 0;
		
		for(int offset = -1; offset < rounds-1; ++offset) {
					
			String jsonResponse = httpGet(
					"http://store.steampowered.com//appreviews/" + appId + "?" + 
					"start_offset=" + offset + "&filter=all&language=english");
		
			// if the string has the same has -> api returned again the first one
			if(firstItemHash == 0) {
				firstItemHash = jsonResponse.hashCode();
			} else if(jsonResponse.hashCode() == firstItemHash) {
				break;
			} 
			
			try {

				// HTML is inside a JSON string
				JsonNode rootNode = new ObjectMapper().readTree(new StringReader(jsonResponse));
				String xmlResponse = rootNode.get("html").asText();

				// convert HTML into review items
				Document doc = Jsoup.parse(xmlResponse);
				Elements reviewItems = doc.getElementsByClass("review_box");

				Iterator<Element> iter = reviewItems.iterator();
				while (iter.hasNext()) {
					Element reviewElement = iter.next();

					Review review = new Review();
					review.setAuthor(reviewElement.getElementsByClass("persona_name").text());
					review.setContent(reviewElement.getElementsByClass("content").text());
					
					String reviewHeader = reviewElement.getElementsByClass("header").text();
					
					if (!reviewHeader.isEmpty()) {
						reviewHeader = reviewHeader.replaceAll(",", "");
						Matcher matcher = NUMBER_PATTERN.matcher(reviewHeader.toLowerCase());

						try {
							matcher.find();
							review.setPeopleHelpful(Long.parseLong(matcher.group()
									.trim()));
							matcher.find();
							review.setPeopleSeen(Long.parseLong(matcher.group()
									.trim()));
						} catch (Exception e) {
							LOG.error("Error review header: " + reviewHeader + "\n", e);
						}
					}

					result.add(review);
				}
				
			} catch (JsonProcessingException e) {
				LOG.error(e);
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		
		return result;
	}
}
