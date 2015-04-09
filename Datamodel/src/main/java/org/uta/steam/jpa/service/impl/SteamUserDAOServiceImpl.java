package org.uta.steam.jpa.service.impl;

import javax.annotation.Resource;

import org.uta.steam.jpa.model.SteamUser;
import org.uta.steam.jpa.model.service.SteamUserDAOService;


@Resource
class SteamUserDAOServiceImpl 
	extends AbstractDAOServiceImpl<SteamUser> 
	implements SteamUserDAOService {

}
