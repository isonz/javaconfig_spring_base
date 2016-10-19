package z.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtils {

	private static final Logger LOGGER = Logger.getLogger(PropertiesUtils.class.getName());

	public static Map<String, String> loadAsMap(InputStream input) {
		if (input == null) {
			throw new IllegalArgumentException("input stream is null.");
		}
		return toMap(load(input));
	}

	public static Properties load(InputStream input) {
		if (input == null) {
			throw new IllegalArgumentException("input stream is null.");
		}
		Properties properties = new Properties();
		try {
			properties.load(input);
		} catch (Exception e) {
			LOGGER.warn(e.getMessage(), e);
			return null;
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				LOGGER.warn(e.getMessage(), e);
			}
		}
		return properties;
	}

	public static Properties load(String resource, Class<?> callingClass) {
		InputStream input = callingClass.getClassLoader().getResourceAsStream(resource);
		return load(input);
	}

	public static Map<String, String> loadAsMap(String resource, Class<?> callingClass) {
		InputStream input = callingClass.getClassLoader().getResourceAsStream(resource);
		return loadAsMap(input);
	}

	public static Map<String, String> toMap(Properties properties) {
		Map<String, String> map = new HashMap<String, String>();
		if (properties == null) {
			return map;
		}
		Enumeration<?> e = properties.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String val = properties.getProperty(key.toString(), "");
			map.put(key, val);
		}
		return map;
	}
}
