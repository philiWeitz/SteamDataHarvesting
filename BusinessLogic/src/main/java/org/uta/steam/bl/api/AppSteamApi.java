package org.uta.steam.bl.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uta.steam.bl.util.PropUtil;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;

public class AppSteamApi extends AbstractSteamApi {

	private static final Pattern VERSION_PATTERN = Pattern
			.compile("(version ([0-9].)+[0-9]+)|(update)");

	private ReviewSteamApi reviewApi = new ReviewSteamApi();
	
	
	public List<SteamApp> getApps() {
		List<SteamApp> result = new LinkedList<SteamApp>();

		String jsonResponse = httpGet(PropUtil.getProperty("steam.get.all.apps.url"));

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

		String xmlResponse = httpGet(PropUtil.getProperty("steam.get.news.url", appId));

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
				Element content = newsitem.getElementsByTag("contents").first();

				AppVersion version = new AppVersion();
				version.setTitle(title.text());
				version.setContent(content.text());
				version.setPublished(published.text());
				result.add(version);
			}
		}

		return result;
	}
	

	public List<Review> getHelpfulAppReviews(long appId) {		
		return reviewApi.getHelpfulAppReviews(appId, 1);
	}
	
	public List<Review> getHelpfulAppReviews(long appId, int rounds) {		
		return reviewApi.getHelpfulAppReviews(appId, rounds);
	}
}
