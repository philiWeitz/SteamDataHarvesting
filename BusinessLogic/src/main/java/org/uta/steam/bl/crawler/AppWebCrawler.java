package org.uta.steam.bl.crawler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uta.steam.bl.util.PropUtil;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.AppDLC;

public class AppWebCrawler extends AbstractWebCrawler {
	private static Logger LOG = LogManager.getLogger(AppWebCrawler.class);
	private static final String BASE_URL = PropUtil.getProperty("steam.get.app.page.url");

	
	private long appId = -1;
	private Document htmlDoc = null;

	
	public List<AppDLC> getDLCs(long appId) {
		List<AppDLC> result = new LinkedList<AppDLC>();

		Document doc = getHtmlFromUrl(appId);
		Elements dlcs = getElementsByClass(doc, "game_area_dlc_row");

		if(null != doc) {
			Iterator<Element> iterator = dlcs.iterator();
			while (iterator.hasNext()) {
				Element dlcItem = iterator.next();
	
				AppDLC dlc = new AppDLC();
				dlc.setName(dlcItem.getElementsByClass("game_area_dlc_name").text());
				dlc.setDlcId(Long.parseLong(dlcItem.attr("data-ds-appid")));
				dlc.setReleaseDate(getAppReleaseDate(appId));
	
				result.add(dlc);
			}
		}

		return result;
	}

	public double getAppPrice(long appId) {
		double result = 0;

		Document doc = getHtmlFromUrl(appId);
		if(null != doc) {
			
			// the game price is always the first element
			Elements elements = getElementsByClass(doc,
					"game_area_purchase_game_wrapper");
	
			if (!elements.isEmpty()) {
				String price = StringUtils.EMPTY;
	
				Elements discountPrice = elements.first().getElementsByClass(
						"discount_original_price");
	
				if (!discountPrice.isEmpty()) {
					price = discountPrice.text();
				} else {
					Elements normalPrice = elements.first().getElementsByClass(
							"game_purchase_price");
					price = normalPrice.text();
				}
	
				try {
					result = Double.parseDouble(price.replace(',', '.').replaceAll(
							"€$", ""));
	
				} catch (Exception e) {
					LOG.error("Error converting price string to double", e);
				}
			}
		}
		
		return result;
	}

	public Date getAppReleaseDate(long appId) {
		Date result = null;

		Document doc = getHtmlFromUrl(appId);
		if(null != doc) {
			
			Elements releaseElements = getElementsByClass(doc, "release_date");

			Iterator<Element> iterator = releaseElements.iterator();
			while (iterator.hasNext()) {
				Element releaseElement = iterator.next();
	
				if (!releaseElement.getElementsByClass("date").isEmpty()) {
					String date = releaseElement.getElementsByClass("date").first()
							.text();
	
					DateFormat format = new SimpleDateFormat(
							SteamUtil.RELEASE_DATE_FORMAT, Locale.ENGLISH);
	
					try {
						result = format.parse(date);
					} catch (ParseException e) {
						LOG.error(e);
					}
	
					break;
				}
			}
		}
		return result;
	}

	public List<String> getAppTags(long appId) {
		List<String> result = new LinkedList<String>();
		
		Document doc = getHtmlFromUrl(appId);

		if(null != doc) {
			Elements elements = getElementsByClass(doc, "app_tag");
			result = getInnerHtml(elements);
	
			if (!result.isEmpty()) {
				// last element is the extention button (+)
				result.remove(result.size() - 1);
			}
		}
		return result;
	}


	private Document getHtmlFromUrl(long appId) {

		if (this.appId != appId) {

			this.appId = appId;
			htmlDoc = super.getHtmlFromUrl(BASE_URL + appId);
		}

		return htmlDoc;
	}
}
