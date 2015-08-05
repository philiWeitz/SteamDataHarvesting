package org.uta.steam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service/user")
public class UserController {

	/*
	 * example call: -
	 * http://localhost:8080/SteamDataHarvestingWebServices/service/user/getCurrentUser
	 */

		
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
	public ResponseEntity<String> getCurrentUser() {

		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context.getAuthentication() != null && 
				context.getAuthentication().isAuthenticated()) {
			
			return new ResponseEntity<String>(context.getAuthentication().getName(), HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
	}
}