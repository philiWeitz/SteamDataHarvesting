package org.uta.steam.rest.test.common;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestUtil {

	private static Logger LOG = LogManager.getLogger(TestUtil.class);

	
	private TestUtil() {
		
	}
		
	public static void setPrivateField(Object obj, String fieldName, Object value) {

		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);		
			field.set(obj, value);
			field.setAccessible(false);
			
		} catch (NoSuchFieldException e) {
			LOG.error("Can't access field \"" + fieldName + "\". Is the field name correct?");
		} catch (SecurityException e) {
			LOG.error(e);
		} catch (IllegalArgumentException e) {
			LOG.error(e);
		} catch (IllegalAccessException e) {
			LOG.error(e);
		}
	}
	
}
