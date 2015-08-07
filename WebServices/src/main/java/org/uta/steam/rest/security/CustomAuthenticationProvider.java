package org.uta.steam.rest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.uta.steam.bl.util.PropUtil;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		// TODO: should be done against a database
		if (name.equals(PropUtil.getProperty("secret.admin.name")) 
				&& passwordEncoder.matches(password, PropUtil.getProperty("secret.admin.password"))) {
			
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			
			Authentication auth = 
					new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
			
			return auth;
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}