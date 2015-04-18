package org.uta.steam.rest;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.jpa.model.SteamApp;

@RestController
@RequestMapping("/service/app")
public class AppController {
	
	/*
	 * example call: -
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/getAllApps
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/getApp/1223421
	 */

	private static Logger LOG = LogManager.getLogger(AppController.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Autowired
	private SteamDataService steamDataService;
	
	
	@RequestMapping(value = "/getAllApps", method = RequestMethod.GET)
	public ResponseEntity<String> getAllApps() {
		String jsonString = StringUtils.EMPTY;
		
		try {
			jsonString = mapper.writeValueAsString(steamDataService.getAllApps());
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}		
		
		return new ResponseEntity<String>(jsonString, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getApp/{appId}", method = RequestMethod.GET)
	public ResponseEntity<String> getApp(@PathVariable long appId) {
		
		String jsonString = StringUtils.EMPTY;
		SteamApp app = steamDataService.getWholeApp(appId);
		
		if(null != app) {
			try {
				jsonString = mapper.writeValueAsString(app);
			} catch (JsonGenerationException e) {
				LOG.error(e);
			} catch (JsonMappingException e) {
				LOG.error(e);
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		
		return new ResponseEntity<String>(jsonString, HttpStatus.OK);
	}	
}