package util;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import io.restassured.RestAssured;
//import resources.Info;


public class Trigger {

	protected static boolean isInit = false ;
	protected static String sessionId = null;
	protected static ExtentReports extentReport = null;
	//protected static Info inf = null;
	
	
	public Trigger() {
		init();
	}
	
	
	public static void init() {
		if (isInit) {
			return ;
		}
		new Configure();	// initialization
		isInit = true ;
		setUp();
		
	}
	

	
	
	@BeforeSuite
	public void beforeSuite(){
		
		//extentReport = new ExtentReports("reports" + File.separator + "ExtentReportsTestNG.html", true);

		//RestAssured.config = RestAssuredConfig.newConfig().httpClient(HttpClientConfig.httpClientConfig().reuseHttpClientInstance());
	}

	@BeforeClass
	public static void setUp(){
		//sessionId =inf.createSessionId(RestUtil.setRequest()).asString();
		sessionId=null;
		RestAssured.baseURI = Configure.getValue("BASE_URL");
		RestAssured.port = Integer.parseInt(Configure.getValue("PORT"));
		RestAssured.basePath = Configure.getValue("BASE_PATH");

	}
	
	
	
}
