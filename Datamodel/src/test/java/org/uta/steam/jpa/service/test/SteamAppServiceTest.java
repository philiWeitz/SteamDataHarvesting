package org.uta.steam.jpa.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.jpa.model.AppInfo;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-context.xml"})
public class SteamAppServiceTest {

	@Autowired
	SteamAppDAOService steamAppService;
	
	
	@Test
	public void saveAppTest() {		
		SteamApp app = createSteamApp();

		// save app to database
		app = steamAppService.saveOrUpdate(app);
		
		app.setName("Changed Name");
		app.getDlcs().clear();
		app.getVersions().clear();
		
		SteamApp appFromDatabase = steamAppService.getById(app.getId());
		appFromDatabase = steamAppService.getById(app.getId());
		appFromDatabase = steamAppService.getById(app.getId());
		
		assertNotSame(app.getName(), appFromDatabase.getName());		
		assertEquals(2, appFromDatabase.getDlcs().size());
		assertEquals(1, appFromDatabase.getVersions().size());
	}


	@Test
	public void appInfoTest() {		
		AppInfo info = new AppInfo();
		info.setAppid(1);
		info.setName("App Name 1");
		
		List<AppInfo> infos = new LinkedList<AppInfo>();
		infos.add(info);
		
		steamAppService.updateAppInfos(infos);
		
		String appName = steamAppService.getAppNameByAppId(info.getAppid());
		assertEquals("App Name 1", appName);
	}
	
	private SteamApp createSteamApp() {
		Set<String> dlcs = new HashSet<String>();
		dlcs.add("DLC 1");
		dlcs.add("DLC 2");
		
		AppVersion version = new AppVersion();
		version.setPublished(new Date());
		version.setTitle("Version 1");
		
		Set<AppVersion> versions = new HashSet<AppVersion>();
		versions.add(version);
		
		SteamApp app = new SteamApp();
		app.setAppid(1);
		app.setName("Test App");
		app.setPrice(19.99);
		app.setDlcs(dlcs);
		app.setVersions(versions);
		
		return app;
	}
}
