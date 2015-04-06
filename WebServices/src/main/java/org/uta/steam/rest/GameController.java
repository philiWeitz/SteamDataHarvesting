package org.uta.steam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/game")
public class GameController {
	
	/*
	 * example call: -
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/game/getAllGames
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/game/getDLCs/1223421
	 */

	@RequestMapping(value = "/getAllGames", method = RequestMethod.GET)
	public ResponseEntity<String> getAllGames() {
		return new ResponseEntity<String>("Get All Games Stub", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getDLCs/{appId}", method = RequestMethod.GET)
	public ResponseEntity<String> getDLCsByGame(@PathVariable long appId) {
		return new ResponseEntity<String>("Get DLCs Stub " + appId, HttpStatus.OK);
	}	
}