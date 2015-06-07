package org.uta.steam.jpa.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;
import org.uta.steam.jpa.service.impl.TestDataServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-context.xml" })
public class SteamAppServiceTest {

	@Autowired
	SteamAppDAOService steamAppService;

	private TestDataServiceImpl testDataService;
	
	@Before
	public void init() {
		testDataService = new TestDataServiceImpl();
		testDataService.createTestData();
	}

	@Test
	public void saveAppTest() {
		assertNotSame(0, testDataService.getAppNoData().getId());

		AppData data = testDataService.getAppWithData().getData().iterator()
				.next();
		assertNotSame(0, data.getId());
		assertNotNull(data.getCreated());
	}

	@Test
	public void getPureAppDataTest() {

		List<SteamApp> fromDatabase = steamAppService.getAll();

		for (SteamApp app : fromDatabase) {
			try {
				app.getData().isEmpty();
				fail();
			} catch (Exception e) {
				// this is expected
			}
		}
	}

	@Test
	public void getFullAppDataTest() {
		SteamApp fromDatabase = steamAppService.getWholeAppById(testDataService
				.getAppWithData().getId());
		assertEquals(testDataService.getAppWithData().getAppId(),
				fromDatabase.getAppId());
		assertEquals(testDataService.getAppWithData().getName(),
				fromDatabase.getName());
		assertEquals(testDataService.getAppWithData().getData().size(),
				fromDatabase.getData().size());
		assertEquals(testDataService.getAppWithData().getDlcs().size(),
				fromDatabase.getDlcs().size());
	}

	@Test
	public void addAppToUpdateListTest() {
		List<SteamApp> appsUpdate = steamAppService.getWholeAppsToUpdate();
		assertTrue(appsUpdate.isEmpty());

		steamAppService.addAppToUpdateList(testDataService.getAppNoData().getAppId());

		appsUpdate = steamAppService.getWholeAppsToUpdate();
		assertEquals(1, appsUpdate.size());
		assertEquals(testDataService.getAppNoData().getAppId(),
				appsUpdate.get(0).getAppId());

		steamAppService.addAppToUpdateList(testDataService.getAppWithData().getAppId());

		appsUpdate = steamAppService.getWholeAppsToUpdate();
		assertEquals(2, appsUpdate.size());
	}

	@Test
	public void removeAppFromUpdateListTest() {
		steamAppService.addAppToUpdateList(testDataService.getAppNoData().getAppId());
		steamAppService.addAppToUpdateList(testDataService.getAppWithData().getAppId());
		
		List<SteamApp> appsUpdate = steamAppService.getWholeAppsToUpdate();
		assertSame(2, appsUpdate.size());
		
		steamAppService.removeAppFromUpdateList(testDataService.getAppNoData().getAppId());		
		
		appsUpdate = steamAppService.getWholeAppsToUpdate();
		assertEquals(1, appsUpdate.size());
		assertEquals(testDataService.getAppWithData().getAppId(),
				appsUpdate.get(0).getAppId());

		steamAppService.removeAppFromUpdateList(testDataService.getAppWithData().getAppId());
		
		appsUpdate = steamAppService.getWholeAppsToUpdate();		
		assertTrue(appsUpdate.isEmpty());
	}
	
	@Test
	public void updateAppList() {
		List<SteamApp> apps = steamAppService.getAll();
		assertEquals(2, apps.size());

		SteamApp appToAdd = new SteamApp();
		appToAdd.setAppId(2);
		appToAdd.setName("App Nr 2");
		apps.add(appToAdd);
		steamAppService.updateAppList(apps);

		List<SteamApp> appsUpdated = steamAppService.getAll();
		assertEquals(3, appsUpdated.size());
	}
}
