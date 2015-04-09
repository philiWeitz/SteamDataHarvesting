package org.uta.steam.bl.test.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.bl.test.util.SteamTestUtil;
import org.uta.steam.jpa.model.SteamUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-context.xml"})
public class SteamDataServiceTest {

	
	@Autowired
	SteamDataService steamDataService;

	@Test
	public void extractAndSaveUserDataTest() {
		// extract the data
		SteamUser user = steamDataService.extractUserData(SteamTestUtil.USER_ID);
		assertFalse(user.getUserName().isEmpty());
		assertFalse(user.getUserId().isEmpty());
		assertFalse(user.getApps().isEmpty());
		assertFalse(user.getAchievements().isEmpty());

		// put the user to the database
		user = steamDataService.saveUserToDatabase(user);
		assertTrue(user.getId() > 0);
	}
}
