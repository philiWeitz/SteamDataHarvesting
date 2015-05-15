package org.uta.steam.jpa.common;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestUtil {

	private static Logger LOG = LogManager.getLogger(TestUtil.class);

	
	private TestUtil() {
		
	}
		
	public static void setPrivateField(Object obj, String fieldName, Object value) {
		setPrivateField(obj, fieldName, value, obj.getClass());
	}

	@SuppressWarnings("rawtypes")
	public static void setPrivateField(Object obj, String fieldName, Object value, Class clazz) {

		try {
			Field field = clazz.getDeclaredField(fieldName);
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
