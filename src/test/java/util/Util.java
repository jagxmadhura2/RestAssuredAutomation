package util;


import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
public class Util {
	public static File getPropertyFilePath(String path) throws URISyntaxException {
		URL url = path.getClass().getResource(path);
		File inputFile = new File(url.toURI());
		return inputFile ;
	}

	public static Properties loadProperties(String path) {
		Properties properties = new Properties();
		FileInputStream fis = null ;
		try {
			File file = getPropertyFilePath(path);
			fis = new FileInputStream(file);
			properties.load(fis);
			fis.close();
		} catch (Exception e) {
			return null ;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					;
				}
			}
		}
		return properties ;
	}

}
