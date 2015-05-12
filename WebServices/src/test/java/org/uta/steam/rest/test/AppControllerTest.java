package org.uta.steam.rest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.service.impl.TestDataServiceImpl;
import org.uta.steam.rest.AppController;
import org.uta.steam.rest.test.common.TestUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-context.xml"})
public class AppControllerTest {

	@Autowired
	private SteamDataService steamDataService;	
	private TestDataServiceImpl testDataService = new TestDataServiceImpl();
	
	private AppController appController = new AppController();
	
	
	@Before
	public void beforeTests() {
		TestUtil.setPrivateField(appController, "steamDataService", steamDataService);
		testDataService.createTestData();
	}
	

	@Test
	public void getAllAppsTest() {
		
		ResponseEntity<String> response = appController.getAllApps();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertFalse(response.getBody().isEmpty());
		assertTrue(response.getBody().contains(testDataService.getAppWithData().getName()));
		assertTrue(response.getBody().contains(testDataService.getAppNoData().getName()));		
		
		testDataService.cleanDatabase();
		
		response = appController.getAllApps();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("[]", response.getBody());
	}

	
	@Test
	public void getAppTest() {
		
		ResponseEntity<String> response = appController.getApp(testDataService.getAppWithData().getAppId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertFalse(response.getBody().isEmpty());
		assertTrue(response.getBody().contains(testDataService.getAppWithData().getName()));
		assertFalse(response.getBody().contains(testDataService.getAppNoData().getName()));
		
		response = appController.getApp(testDataService.getAppNoData().getAppId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertFalse(response.getBody().isEmpty());
		assertTrue(response.getBody().contains(testDataService.getAppNoData().getName()));
		assertFalse(response.getBody().contains(testDataService.getAppWithData().getName()));
		
		response = appController.getApp(123456789);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertFalse(response.getBody().contains(testDataService.getAppNoData().getName()));
		assertFalse(response.getBody().contains(testDataService.getAppWithData().getName()));
	}
	
	@Test
	public void addAndRemoveFromWatchListTest() {
		ResponseEntity<String> response = 
				appController.getApp(testDataService.getAppNoData().getAppId());
		assertTrue(response.getBody().contains("\"getsUpdated\":false"));
	
		appController.addToWatchList(
				String.valueOf(testDataService.getAppNoData().getAppId()), null);
		
		response = appController.getApp(testDataService.getAppNoData().getAppId());
		assertTrue(response.getBody().contains("\"getsUpdated\":true"));
		
		appController.removeFromWatchList(
				String.valueOf(testDataService.getAppNoData().getAppId()), null);
		
		response = appController.getApp(testDataService.getAppNoData().getAppId());
		assertTrue(response.getBody().contains("\"getsUpdated\":false"));		
	}
	
	@Test
	public void checkNumberFormatTest() {
		ResponseEntity<String> response = appController.addToWatchList("1 or '1'='1'", null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		response = appController.removeFromWatchList("1 or '1'='1'", null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
}
