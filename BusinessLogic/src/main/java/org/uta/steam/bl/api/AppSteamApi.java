package org.uta.steam.bl.api;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.SteamApp;

public class AppSteamApi extends AbstractSteamApi {

	private static final Pattern VERSION_PATTERN = 
			Pattern.compile("(version ([0-9].)+[0-9]+)|(update)");
	
	private static Logger LOG = LogManager.getLogger(AppSteamApi.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	public SteamApp[] getApps() {
	
		String jsonResult =
				httpGet("http://api.steampowered.com/ISteamApps/GetAppList/v0001/?format=json");

		jsonResult = jsonResult.replace("\t","")
				.replace("{\"applist\": {\"apps\": {\"app\": ", StringUtils.EMPTY)
				.replace("}}}", StringUtils.EMPTY);
		
		try {
			return mapper.readValue(jsonResult, SteamApp[].class);			
		} catch (JsonParseException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
		
		return new SteamApp[] {};
	}
	
	
	public List<AppVersion> getVersions(long appId) {
		List<AppVersion> result = new LinkedList<AppVersion>();
		
		String xmlResponse = httpGet(
				"https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?format=xml&maxlength=1&"
				+ "key=" + SteamUtil.API_KEY 
				+ "&appid=" + appId);
		
		Document doc = Jsoup.parse(xmlResponse);
		Elements newsItems = doc.getElementsByTag("newsitem");

		Iterator<Element> iter = newsItems.iterator();	
		while(iter.hasNext()) {
			Element newsitem = iter.next();
			Element title = newsitem.getElementsByTag("title").first();

			Matcher matcher = VERSION_PATTERN.matcher(title.text().toLowerCase());			
			if(matcher.find()) {
				Element published = newsitem.getElementsByTag("date").first();
				
				AppVersion version = new AppVersion();
				version.setTitle(title.text());
				version.setPublished(published.text());
				result.add(version);
			}
		}
		
		return result;
	}
}
