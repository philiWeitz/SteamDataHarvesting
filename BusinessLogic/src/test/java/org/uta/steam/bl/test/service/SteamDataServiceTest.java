package org.uta.steam.bl.test.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.model.service.SteamAppDAOService;
import org.uta.steam.jpa.service.impl.TestDataServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-context.xml" })
public class SteamDataServiceTest {

	private TestDataServiceImpl testDataService = new TestDataServiceImpl();

	@Autowired
	private SteamDataService steamDataService;

	@Autowired
	private SteamAppDAOService steamAppDaoService;

	@Before
	public void init() {
		testDataService.createTestData();
	}

	@Test
	public void getAppsTest() {
		List<SteamApp> apps = steamDataService.getAllApps();
		assertNotSame(0, apps.size());
	}

	@Test
	public void setAppUpdateListTest() {
		List<Long> appIds = new LinkedList<Long>();
		appIds.add(testDataService.getAppNoData().getAppId());
		appIds.add(testDataService.getAppWithData().getAppId());

		steamDataService.setAppUpdateList(appIds);
		List<SteamApp> apps = steamDataService.getAllApps();

		assertFalse(apps.isEmpty());
		for (SteamApp app : apps) {
			assertTrue(app.isGetsUpdated());
		}
	}

	@Test
	public void updateAppDataFromSteamTest() {

		SteamApp app = steamDataService.getWholeApp(testDataService
				.getAppWithData().getAppId());

		app.getData().clear();
		app.getDlcs().clear();
		app.getVersions().clear();
		app = steamAppDaoService.saveOrUpdate(app);

		List<Long> appIds = new LinkedList<Long>();
		appIds.add(testDataService.getAppNoData().getAppId());
		appIds.add(testDataService.getAppWithData().getAppId());

		steamDataService.setAppUpdateList(appIds);
		steamDataService.harvestDataFromSteam();

		app = steamDataService.getWholeApp(app.getAppId());
		assertNotSame(0, app.getData().size());
		assertNotSame(0, app.getDlcs().size());
		assertNotSame(0, app.getVersions().size());
	}
}
