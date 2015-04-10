package org.uta.steam.jpa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.uta.steam.jpa.model.AppInfo;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;


@Resource
class SteamAppDAOServiceImpl 
	extends AbstractDAOServiceImpl<SteamApp> 
	implements SteamAppDAOService {

	private AppInfoDAOServiceImpl appInfoService = new AppInfoDAOServiceImpl();
	

	public void updateAppInfos(List<AppInfo> infos) {
		for(AppInfo info : infos) {
			appInfoService.saveOrUpdate(info);
		}
	}
	
	
	public String getAppNameByAppId(long appId) {
		String result = StringUtils.EMPTY;
		
		List<AppInfo> appInfos = appInfoService.issueQuery(
				"SELECT e FROM " + AppInfo.class.getSimpleName() + " e "
						+ "where e.appid = " + appId);
		
		if(!appInfos.isEmpty()) {
			result = appInfos.get(0).getName();
		}
		
		return result;
	}
}
