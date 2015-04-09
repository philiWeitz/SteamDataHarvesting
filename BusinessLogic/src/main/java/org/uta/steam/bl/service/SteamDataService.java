package org.uta.steam.bl.service;

import org.uta.steam.jpa.model.SteamUser;

public interface SteamDataService {
	SteamUser extractUserData(String userId);
	SteamUser saveUserToDatabase(SteamUser user);
}
