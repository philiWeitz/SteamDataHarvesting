package org.uta.steam.rest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
import org.uta.steam.jpa.test.common.TestUtil;
import org.uta.steam.rest.AppController;


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
	public void checkgetAllAppsAndUpdateListInput() {
		ResponseEntity<String> response = 
				appController.getAllAppsAndUpdateList("'1'='1'", null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		response = appController.getAllAppsAndUpdateList("Half-Life: 2.3, Test Version", null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		response = appController.getAllAppsAndUpdateList("", null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		response = appController.getAllAppsAndUpdateList(null, null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertFalse(response.getBody().isEmpty());		
	}
}
