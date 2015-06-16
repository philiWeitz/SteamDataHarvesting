package org.uta.steam.bl.test.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.SteamApp;
import org.uta.steam.jpa.service.SteamAppDAOService;
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
	public void addAppToUpdateListTest() {
		steamAppDaoService.addAppToUpdateList(testDataService.getAppNoData().getAppId());
		steamAppDaoService.addAppToUpdateList(testDataService.getAppWithData().getAppId());
		
		List<SteamApp> apps = steamDataService.
				getAllAppsAndUpdateList(StringUtils.EMPTY, 0);

		assertFalse(apps.isEmpty());
		for (SteamApp app : apps) {
			assertTrue(app.isGetsUpdated());
		}
	}

	
	@Test
	public void csvExportTest() {
		File file = steamDataService.createCsvFile(testDataService.getAppWithData().getAppId());
		assertNotNull(file);
		assertTrue(file.exists());
	}
}
