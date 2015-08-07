package org.uta.steam.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
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
		
		http.authenticationProvider(new CustomAuthenticationProvider());
		
		http.csrf().csrfTokenRepository(csrfTokenRepository());
		
		// only deactivate CSRF protection while development!
		//http.csrf().disable();
	}
	
    
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        	.withUser(PropUtil.getProperty("secret.admin.name"))
        	.password(PropUtil.getProperty("secret.admin.password"))
        	.roles("ADMIN");
    }
	

	/* angular sets the CSRF token with the name "X-XSRF-TOKEN" but spring expects
	 * it with a different name -> change the name */
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		
		return repository;
	}
}

