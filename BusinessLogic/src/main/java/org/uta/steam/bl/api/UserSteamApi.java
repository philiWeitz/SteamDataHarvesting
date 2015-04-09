package org.uta.steam.bl.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.UserAchievementSummary;
import org.uta.steam.jpa.model.UserApp;

public class UserSteamApi extends AbstractSteamApi {

	
	public String getUserName(String userId) {
		String result = StringUtils.EMPTY;
		
		String xmlResponse = httpGet(
				"http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?format=xml&"
				+ "key=" + SteamUtil.API_KEY 
				+ "&steamids=" + userId);
		
		Document doc = Jsoup.parse(xmlResponse);
		Elements nameItem = doc.getElementsByTag("realname");
		
		if(!nameItem.isEmpty()) {
			result = nameItem.first().text();
		}
		
		return result;
	}

	
	public List<UserApp> getGames(String userId) {		
		List<UserApp> result = new LinkedList<UserApp>();
		
		String xmlResponse = httpGet(
				"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?format=xml&"
				+ "key=" + SteamUtil.API_KEY 
				+ "&steamid=" + userId);
		
		Document doc = Jsoup.parse(xmlResponse);
		Elements gameItems = doc.getElementsByTag("message");

		Iterator<Element> gameIter = gameItems.iterator();
		while(gameIter.hasNext()) {
			Element game = gameIter.next();
			
			UserApp app = new UserApp();
			app.setAppid(game.getElementsByTag("appid").text());
			app.setPlaytime(game.getElementsByTag("playtime_forever").text());	
			
			result.add(app);
		}
		
		return result;
	}
	
	
	public UserAchievementSummary getUserAchievementsByAppId(String userId, long appId) {		

		String xmlResponse = httpGet(
				"https://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v1/?format=xml&"
				+ "key=" + SteamUtil.API_KEY 
				+ "&steamid=" + userId
				+ "&appid=" + appId);
		
		Document doc = Jsoup.parse(xmlResponse);
		Elements achievementItems = doc.getElementsByTag("achievement");

		long achievCount = 0;
		
		Iterator<Element> iterator = achievementItems.iterator();
		while(iterator.hasNext()) {
			Element achieved = iterator.next().getElementsByTag("achieved").first();
			
			if(Integer.parseInt(achieved.text()) > 0) {
				++achievCount;
			}
		}
		
		UserAchievementSummary result = new UserAchievementSummary();
		result.setAppId(appId);
		result.setTimestamp(new Date());
		result.setAchieved(achievCount);
		result.setMaximumAchievements(achievementItems.size());
		
		return result;
	}
	
	public Map<String,Long> getUserStatisticsByAppId(String userId, long appId) {
		Map<String,Long> result = new HashMap<String, Long>();
		
		String xmlResponse = httpGet(
				"http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?format=xml&"
				+ "key=" + SteamUtil.API_KEY 
				+ "&steamid=" + userId
				+ "&appid=" + appId);
		
		Document doc = Jsoup.parse(xmlResponse);
		Elements statItems = doc.getElementsByTag("stat");

		Iterator<Element> iterator = statItems.iterator();
		while(iterator.hasNext()) {
			Element statItem = iterator.next();
			
			String key = statItem.getElementsByTag("name").text();
			long value = Long.parseLong(statItem.getElementsByTag("value").text());			
			
			result.put(key, value);
		}
		
		return result;
	}
}
