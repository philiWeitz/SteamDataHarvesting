package org.uta.steam.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.uta.steam.bl.service.SteamDataHarvestingService;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.SteamApp;


@RestController
@RequestMapping("/service/app")
public class AppController {

	/*
	 * example call: -
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/getAllAppsAndUpdateList?searchTerm=test&max=20
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/getAppDlcs/226840
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/harvestDataForApp/226840
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/getAppsWhichHaveData
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/getCSVFile/226840
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/app/getAppData/226840	
	 */

	private static Logger LOG = LogManager.getLogger(AppController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private SteamDataService steamDataService;
	@Autowired
	private SteamDataHarvestingService dataHarvestingService;
		
	@RequestMapping(value = "/getAllAppsAndUpdateList", method = RequestMethod.GET)
	public ResponseEntity<String> getAllAppsAndUpdateList(String searchTerm, Integer max) {
		String jsonString = StringUtils.EMPTY;

		// validate URL parameter
		if(null == searchTerm) {
			searchTerm = StringUtils.EMPTY;
		}
		if(null == max) {
			max = Integer.MAX_VALUE;
		}
		
		// test for invalid string input
		if(!SteamUtil.STRING_INPUT.matcher(searchTerm).matches()) {
			return new ResponseEntity<String>("Invalid search term", HttpStatus.BAD_REQUEST);
		}
		
		try {
			jsonString = mapper.writeValueAsString(
					steamDataService.getAllAppsAndUpdateList(searchTerm, max));
			
			return new ResponseEntity<String>(jsonString, HttpStatus.OK);
			
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}

		return new ResponseEntity<String>("Can't get steam apps", HttpStatus.BAD_REQUEST);
	}

	
	@RequestMapping(value = "/getAppDlcs/{dlcId}", method = RequestMethod.GET)
	public ResponseEntity<String> getAppDlcs(@PathVariable long dlcId) {

		try {
			String jsonString = StringUtils.EMPTY;
			List<AppDLC> dlcs = steamDataService.getDlcsByAppId(dlcId);

			jsonString = mapper.writeValueAsString(dlcs);
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
	
	
	@RequestMapping(value = "/harvestDataForApp/{appId}", method = RequestMethod.GET)
	public ResponseEntity<String> harvestDataForApp(@PathVariable long appId) {
		dataHarvestingService.harvestDataFromSteam(appId);
		return new ResponseEntity<String>("Done", HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "/getAppData/{appId}", method = RequestMethod.GET)
	public ResponseEntity<String> getAppData(@PathVariable long appId) {
		
		try {
			String jsonString = StringUtils.EMPTY;
			List<AppData> data = steamDataService.getAppDataById(appId);
			Collections.sort(data);
			
			jsonString = mapper.writeValueAsString(data);
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
	
	
    @RequestMapping(value = "/addToWatchList", method = RequestMethod.POST)
    public ResponseEntity<String> addToWatchList(@RequestBody long appId, UriComponentsBuilder builder) {
    	
    	if(steamDataService.addAppToUpdateList(appId)) {
    		return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
    	
		return new ResponseEntity<String>("AppId not found", HttpStatus.OK);
    }	
    
    
    @RequestMapping(value = "/removeFromWatchList", method = RequestMethod.POST)
    public ResponseEntity<String> removeFromWatchList(@RequestBody long appId, UriComponentsBuilder builder) {

    	if(steamDataService.removeAppFromUpdateList(appId)) {
    		return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
    	
		return new ResponseEntity<String>("AppId not found", HttpStatus.OK);
    }
    
    
	@RequestMapping(value = "/getAppsWhichHaveData", method = RequestMethod.GET)
	public ResponseEntity<String> getAppsWhichHaveData() {

		try {
			String jsonString = StringUtils.EMPTY;
			List<SteamApp> apps = steamDataService.getAppsWhichHaveData();

			jsonString = mapper.writeValueAsString(apps);
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
	
    
    @RequestMapping(value = "/getCSVFile/{appId}", method = RequestMethod.GET)
    public void getFile(@PathVariable long appId, HttpServletResponse response) {
    
    	try {
    		String path = steamDataService.createCsvFile(appId);
    		InputStream is = new FileInputStream(path);

    		response.setHeader("Content-Disposition","attachment; "
    				+ "filename=csv-file-" + appId + ".csv");
    		
    		response.setContentType("text/plain");
    		response.setCharacterEncoding(SteamUtil.CSV_OUTPUT_FORMAT);

    		IOUtils.copy(is, response.getOutputStream());
    		response.flushBuffer();
        } catch (IOException ex) {
        	LOG.error("Error creating csv file");
        }
    }
}