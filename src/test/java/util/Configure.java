package util;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
public class Configure {
	private static boolean isInit = false ;
	private static String path = null ;
	private static Properties prop = null ;
	private static String logPath = null ;

	public Configure() {
		if (isInit) {
			return ;
		}
		init();
	}

	private void init() {
		Configure.path = "/configure.properties" ;
		System.out.println(path);
		prop = Util.loadProperties(Configure.path);
		if (prop == null) {
			throw new RuntimeException("Unable to load properties file: " + Configure.path);
		}
		initLog4j();
		logPath = "logs" + File.separator;

		isInit = true ;
	}

	/* Log4j appender is located at a different dir, so using programmatically initialization */
	private static void initLog4j() {
		try {
			Properties properties = new Properties();
			File file = Util.getPropertyFilePath("/log4j.xml");
			DOMConfigurator.configure(file.getAbsolutePath());
		} catch (Exception e) {
			RuntimeException fe = new RuntimeException("Couldn't initialize log4j.", e);
			throw fe ;
		}
	}

	public static String getValue(String key) {
		return prop.getProperty(key);
	}

}
