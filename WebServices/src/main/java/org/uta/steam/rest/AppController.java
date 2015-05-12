package org.uta.steam.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.uta.steam.bl.service.SteamDataService;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.SteamApp;

@Component
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
	

	@RequestMapping(value = "/getApp/{appId}", method = RequestMethod.GET)
	public ResponseEntity<String> getApp(@PathVariable long appId) {

		String jsonString = StringUtils.EMPTY;
		SteamApp app = steamDataService.getWholeApp(appId);

		if (null != app) {
			try {
				jsonString = mapper.writeValueAsString(app);
				return new ResponseEntity<String>(jsonString, HttpStatus.OK);
				
			} catch (JsonGenerationException e) {
				LOG.error(e);
			} catch (JsonMappingException e) {
				LOG.error(e);
			} catch (IOException e) {
				LOG.error(e);
			}
		}

		return new ResponseEntity<String>("Can't find appid", HttpStatus.NOT_FOUND);
	}
	
	
    @RequestMapping(value = "/addToWatchList", method = RequestMethod.POST)
    public ResponseEntity<String> addToWatchList(@RequestBody String appIdString, UriComponentsBuilder builder) {
    	
    	try {
			Long appId = Long.parseLong(appIdString);
			
			if(steamDataService.addAppToUpdateList(appId)) {
				return new ResponseEntity<String>("OK", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("AppId not found", HttpStatus.OK);
			}
			
		} catch (NumberFormatException e) {
			LOG.error(e);
		}
    	
    	return new ResponseEntity<String>("Wrong Number Format", HttpStatus.BAD_REQUEST);
    }	
    
    
    @RequestMapping(value = "/removeFromWatchList", method = RequestMethod.POST)
    public ResponseEntity<String> removeFromWatchList(@RequestBody String appIdString, UriComponentsBuilder builder) {
    	
    	try {
			Long appId = Long.parseLong(appIdString);
			
			if(steamDataService.removeAppFromUpdateList(appId)) {
				return new ResponseEntity<String>("OK", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("AppId not found", HttpStatus.OK);
			}
	        
		} catch (NumberFormatException e) {
			LOG.error(e);
		}
    	
    	return new ResponseEntity<String>("Wrong Number Format", HttpStatus.BAD_REQUEST);
    }
    
    
    @RequestMapping(value = "/getCSVFile/{appId}", method = RequestMethod.GET)
    public void getFile(@PathVariable long appId, HttpServletResponse response) {
    
    	try {
    		String path = steamDataService.createCsvFile(appId);
    		InputStream is = new FileInputStream(path);

    		response.setHeader("Content-Disposition","attachment; filename=csv-file.txt");
    		response.setContentType("text/plain");
    		response.setCharacterEncoding(SteamUtil.CSV_OUTPUT_FORMAT);

    		IOUtils.copy(is, response.getOutputStream());
    		response.flushBuffer();
        } catch (IOException ex) {
        	LOG.error("Error creating csv file");
        }
    }
}