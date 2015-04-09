package org.uta.steam.jpa.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uta.steam.jpa.model.SteamUser;
import org.uta.steam.jpa.model.UserAchievementSummary;
import org.uta.steam.jpa.model.UserApp;
import org.uta.steam.jpa.model.service.SteamUserDAOService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-context.xml"})
public class SteamUserServiceTest {

	@Autowired
	SteamUserDAOService steamUserService;
	
	
	@Test
	public void saveUserTest() {		
		SteamUser user = createSteamUser();
		user = steamUserService.saveOrUpdate(user);
		
		// change original user
		user.setUserName("Changed User Name");
		user.getAchievements().clear();
		user.getApps().clear();
		
		// get the user multiple times
		SteamUser userFromDatabase = steamUserService.getById(user.getId());
		userFromDatabase = steamUserService.getById(user.getId());
		userFromDatabase = steamUserService.getById(user.getId());
		
		// check that everything is still there
		assertNotSame(user.getUserName(), userFromDatabase.getUserName());	
		assertEquals(2, userFromDatabase.getApps().size());
		assertEquals(1, userFromDatabase.getAchievements().size());		
	}

	
	@Test
	public void saveUserContentTest() {		
		SteamUser user = createSteamUser();
		user = steamUserService.saveOrUpdate(user);
		
		// get the user from the database
		SteamUser userFromDatabase = steamUserService.getById(user.getId());
		
		// check that everything is still there
		assertEquals(user.getUserName(), userFromDatabase.getUserName());	
		assertEquals(2, userFromDatabase.getApps().size());
		assertEquals(1, userFromDatabase.getAchievements().size());		
		
		userFromDatabase.getApps().remove(
				userFromDatabase.getApps().iterator().next());		
		userFromDatabase.getAchievements().clear();
		
		userFromDatabase = steamUserService.saveOrUpdate(userFromDatabase);
		
		SteamUser userFromDatabase2 = steamUserService.getById(user.getId());
		assertEquals(1, userFromDatabase2.getApps().size());
		assertEquals(0, userFromDatabase2.getAchievements().size());		
	}
	
	
	private SteamUser createSteamUser() {
		UserApp app1 = new UserApp();
		app1.setAppid(1);
		app1.setPlaytime(100);

		UserApp app2 = new UserApp();
		app2.setAppid(2);
		app2.setPlaytime(200);
		
		UserAchievementSummary achievement1 = new UserAchievementSummary();
		achievement1.setAppId(1);
		achievement1.setAchieved(10);
		achievement1.setMaximumAchievements(100);
		achievement1.setTimestamp(new Date());

		SteamUser user = new SteamUser();
		user.setUserId("AVB323423SDFG");
		user.setUserName("Test User");
		user.getApps().add(app1);
		user.getApps().add(app2);
		
		user.getAchievements().add(achievement1);
		
		return user;
	}
}
