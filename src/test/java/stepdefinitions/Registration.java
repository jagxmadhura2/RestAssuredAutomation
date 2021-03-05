package stepdefinitions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.java.en.Then;
import implementer.Registerimpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.RestUtil;
import util.Trigger;
import validator.RegistrationValidator;
import validator.UserValidator;

public class Registration extends Trigger{


	String payload;
	protected static ExtentReports extentReport = null;
	RegistrationValidator validateRegistration = new RegistrationValidator();

	Date dt = new Date( );
	Date dt1 = new Date();
	Calendar c = Calendar.getInstance();
	SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd");
	public String creationDate  = ft.format(dt),endDate;

	public  int userId;
	public String token;
	
	public static boolean validatestatuscode = false;



	JSONObject JSONResponseBody = null, obj = null;
	JSONArray JSONArrayResponse=null;
	protected static String sessionId = null;
	private Logger logger = Logger.getLogger(this.getClass());

	
	@BeforeSuite
	public void bt() {

		extentReport = new ExtentReports("reports" + File.separator + "ExtentReportsTestNG.html", true);

	}

	@AfterSuite

	public void as() {

		extentReport.flush();
	}
	
	
	
	@Test(priority=1)
	@Then("^automate register user api$")
	public void automate_register_user_api() {
		creationDate = ft.format(dt);

		//use only for testng framework
		/*	ExtentTest test = extentReport.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		test.assignCategory("Automation API BVT Suite - Demo");
		test.assignAuthor("Jagadeesh");
		 */	
		payload = RestUtil.getPayload("regression/user/RegisterSuccessful.json");
		obj = new JSONObject(payload);

		//the below values will override with the default values which is present in CreateUser
		obj.put("email", "jagadeeshmadhura2@gmail.com");
		obj.put("password", "secret");
		payload = obj.toString();

		logger.info("printing payload" +payload);

		Response res = Registerimpl.registerUser(payload);


		boolean statusvalidate = RestUtil.validateStatusCode200(res);

		logger.info(res.getStatusCode());

		// validation of the response
		String jsonString = res.asString();	
		// use https://jsonpathfinder.com/ for finding the json path
		userId = JsonPath.from(jsonString).get("id");
		token =JsonPath.from(jsonString).getString("token");

		validateRegistration.RegistrationValidator(obj, userId, token);

		logger.info("User registration created succcessfully");


		//use only for testing
		//extentReport.endTest(test);

	}

	
	
}
