package org.uta.steam.bl.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropUtil {
	
	private static Logger LOG = LogManager.getLogger(PropUtil.class);
	private static Properties prop;


	private static Properties getProp() {
		
		if(null == prop) {
			try {
				prop = new Properties();		

				InputStream input;

				// try to load the resource from current context first
				input = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("config.properties");
				
				// if not available try to load it from the business logic context
				if(null == input) {
					input = PropUtil.class.
							getClassLoader().getResourceAsStream("config.properties");
				}
				
				prop.load(input);
				
			} catch (IOException e) {
				LOG.error("Couldn't load properties file config.properties!", e);
			}
		}		
		return prop;
	}
	
	public static String getProperty(String key) {
		return getProp().getProperty(key, StringUtils.EMPTY);
	}

	public static String getProperty(String key, Object... arg) {
		try {
			return MessageFormat.format(getProp().getProperty(key, StringUtils.EMPTY), arg);
		} catch(IllegalArgumentException e) {
			LOG.error("Illegal argument for detected! (" + key + ")");
		}
		return StringUtils.EMPTY;
	}
	
	public static boolean getPropertyAsBoolean(String key) {
		return Boolean.parseBoolean(getProp().getProperty(key, StringUtils.EMPTY));
	}

	public static Integer getPropertyAsInteger(String key) {
		try {
			return Integer.parseInt(getProp().getProperty(key, StringUtils.EMPTY));
		} catch(Exception e) {
			LOG.error("Property is no integer! (" + key + ")");
		}
		return 0;
	}
}
