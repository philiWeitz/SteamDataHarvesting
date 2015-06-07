package org.uta.steam.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;


    public static ApplicationContext getContext() {
        return context;
    }
    
    
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
}
