package org.uta.steam.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.uta.steam.bl.util.PropUtil;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		LoginAuthenticationHandler loginHandler = new LoginAuthenticationHandler();
		
		http
			.exceptionHandling().authenticationEntryPoint(loginHandler)
				.and()
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin().failureHandler(loginHandler)
				.and()
			.logout()
				.and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}
	
    
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        	.withUser(PropUtil.getProperty("secret.admin.name"))
        	.password(PropUtil.getProperty("secret.admin.password"))
        	.roles("ADMIN");
    }
}

