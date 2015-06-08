package org.uta.steam.bl.test.service;

import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.bl.service.SteamDataHarvestingService;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.service.SteamAppDAOService;
import org.uta.steam.jpa.service.impl.TestDataServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-context.xml" })
public class SteamDataHarvestingServiceTest {

	private TestDataServiceImpl testDataService = new TestDataServiceImpl();

	@Autowired
	private SteamDataHarvestingService steamDataHarvestingService;

	@Autowired
	private SteamAppDAOService steamAppDaoService;

	
	@Before
	public void init() {
		testDataService.createTestData();
	}


	@Test
	public void updateAppDataFromSteamTest() {

		SteamApp app = steamAppDaoService.getWholeAppByAppId(testDataService
				.getAppWithData().getAppId());

		app.getData().clear();
		app.getDlcs().clear();
		app.getVersions().clear();
		app = steamAppDaoService.saveOrUpdate(app);

		steamAppDaoService.addAppToUpdateList(testDataService.getAppNoData().getAppId());
		steamAppDaoService.addAppToUpdateList(testDataService.getAppWithData().getAppId());

		steamDataHarvestingService.harvestDataFromSteam();

		app = steamAppDaoService.getWholeAppByAppId(app.getAppId());
		assertNotSame(0, app.getData().size());
		assertNotSame(0, app.getDlcs().size());
		assertNotSame(0, app.getVersions().size());
	}
}
