package org.uta.steam.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
import org.uta.steam.jpa.model.AppVersion;


@RestController
@RequestMapping("/service/version")
public class VersionController {

	/*
	 * example call: -
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/version/getVersionsByAppId/226840
	 */

	private static Logger LOG = LogManager.getLogger(VersionController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private SteamDataService steamDataService;

		
	@RequestMapping(value = "/getVersionsByAppId/{appId}", method = RequestMethod.GET)
	public ResponseEntity<String> getVersionsByAppId(@PathVariable long appId) {
		String jsonString = StringUtils.EMPTY;

		try {
			List<AppVersion> versions = steamDataService.getVersionsByAppId(appId);
			
			// sort the versions based on published date
			Collections.sort(versions);

			jsonString = mapper.writeValueAsString(versions);
			return new ResponseEntity<String>(jsonString, HttpStatus.OK);
			
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}

		return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
	}
}