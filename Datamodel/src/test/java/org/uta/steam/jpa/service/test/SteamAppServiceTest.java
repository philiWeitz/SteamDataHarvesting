package org.uta.steam.jpa.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
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
@ContextConfiguration(locations = {"/spring-test-context.xml"})
public class SteamAppServiceTest {

	private TestDataServiceImpl testDataService = new TestDataServiceImpl();

	@Autowired
	SteamAppDAOService steamAppService;
		
	
	@Before
	public void init() {
		testDataService.createTestData();
	}
	
	
	@Test
	public void saveAppTest() {		
		assertNotSame(0, testDataService.getAppNoData().getId());
		
		AppData data = testDataService.getAppWithData().getData().iterator().next();
		assertNotSame(0, data.getId());
		assertNotNull(data.getCreated());
	}

	
	@Test
	public void getPureAppDataTest() {		

		List<SteamApp> fromDatabase = steamAppService.getAll();
		
		for(SteamApp app : fromDatabase) {
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
		SteamApp fromDatabase = steamAppService.getWholeAppById(testDataService.getAppWithData().getId());
		assertEquals(testDataService.getAppWithData().getAppId(), fromDatabase.getAppId());
		assertEquals(testDataService.getAppWithData().getName(), fromDatabase.getName());
		assertEquals(testDataService.getAppWithData().getData().size(), fromDatabase.getData().size());
		assertEquals(testDataService.getAppWithData().getDlcs().size(), fromDatabase.getDlcs().size());
	}
}
