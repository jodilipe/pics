package dk.japps.pics;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class PicsProperties {
  private static Logger logger = Logger.getLogger(PicsProperties.class.getName());
  private static Properties properties = new Properties();

  static {
    InputStream is = PicsProperties.class.getResourceAsStream("/application.properties");
    try {
      properties.load(is);
      is.close();
    } catch (Exception e) {
      logger.log(Level.SEVERE, "loading application.properties", e);
    }
  }
  
  public static String getProperty(String key) {
    return properties.getProperty(key);
  }
  
  public static String getProperty(String key, String defaultValue) {
	String property = properties.getProperty(key);
	return property != null ? property : defaultValue;
  }
  
}
