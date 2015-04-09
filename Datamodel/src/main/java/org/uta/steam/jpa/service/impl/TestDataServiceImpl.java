package org.uta.steam.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.uta.steam.jpa.model.service.SteamUserDAOService;


public class TestDataServiceImpl {

	@Autowired
	private SteamUserDAOService steamUserService;
	

	public void createTestData() {
		createSteamUserTestData();
	}
	
	private void createSteamUserTestData() {
		
	}
}
