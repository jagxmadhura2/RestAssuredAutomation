package stepdefinitions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import groovy.util.logging.Log;
import groovy.util.logging.Log4j2;
import implementer.Registerimpl;
import implementer.Userimpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.Configure;
import util.RestUtil;
import util.Trigger;
import validator.UserValidator;


public class User extends Trigger{


	String payload;
	protected static ExtentReports extentReport = null;

	Date dt = new Date( );
	Date dt1 = new Date();
	Calendar c = Calendar.getInstance();
	SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd");
	public String creationDate  = ft.format(dt),endDate;

	public  int userId,length, normalProjectId,roleId;
	public String userJob,username,email,token;
	
	public static boolean validatestatuscode = false;


	UserValidator validateUser = new UserValidator();

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


	

	@Test(priority=2)
	@Then("^automate create user api$")
	public void automate_create_user_api() {
		creationDate = ft.format(dt);

		//use only for testng framework
		/*	ExtentTest test = extentReport.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		test.assignCategory("Automation API BVT Suite - Demo");
		test.assignAuthor("Jagadeesh");
		 */	
		payload = RestUtil.getPayload("regression/user/CreateUser.json");
		obj = new JSONObject(payload);

		//the below values will override with the default values which is present in CreateUser
		//obj.put("name", "jagadeesh");
		//obj.put("job", "QA Lead");
		//payload = obj.toString();

		logger.info(payload);
		Response res = Userimpl.createuser(payload);
		boolean statusvalidate = RestUtil.validateStatusCode201(res);

		logger.info(res.getStatusCode());

		// validation of the response
		String jsonString = res.asString();	
		// use https://jsonpathfinder.com/ for finding the json path
		username = JsonPath.from(jsonString).get("name");
		userJob =JsonPath.from(jsonString).getString("job");

		validateUser.UserValidator(obj, username, userJob);

		logger.info("User registration created succcessfully");


		//use only for testing
		//extentReport.endTest(test);

	}



	@Test
	@Given("^get single user api$")
	public void get_single_user_api() {
		creationDate = ft.format(dt);
		
		Response res = Userimpl.getsingleuserinfo();
		boolean statusvalidate = RestUtil.validateStatusCode200(res);
    	

	}

	
	@Test
	@Given("^get list of user api$")
    public void get_list_of_user_api() {
       
    }

	@Test
    @Given("^update user info using put method$")
    public void update_user_info_using_put_method() {
    	
    	
    	creationDate = ft.format(dt);

		
		payload = RestUtil.getPayload("regression/user/CreateUser.json");
		obj = new JSONObject(payload);

		//the below values will override with the default values which is present in CreateUser
		obj.put("name", "jagadeesh_updated");
		obj.put("job", "QA Lead_updated");
		payload = obj.toString();

		System.out.println(payload);


		Response res = Userimpl.updateUserByPutMethod(payload);


		boolean statusvalidate = RestUtil.validateStatusCode200(res);

		logger.info(res.getStatusCode());

		// validation of the response
		String jsonString = res.asString();	
		// use https://jsonpathfinder.com/ for finding the json path
		username = JsonPath.from(jsonString).get("name");
		userJob =JsonPath.from(jsonString).getString("job");

		validateUser.UserValidator(obj, username, userJob);

		logger.info("User updated using put method succcessfully");
    	
    	
        
    }

	@Test
    @Given("^update user info using patch method$")
    public void update_user_info_using_patch_method() {
        
creationDate = ft.format(dt);

		
		payload = RestUtil.getPayload("regression/user/CreateUser.json");
		obj = new JSONObject(payload);

		//the below values will override with the default values which is present in CreateUser
		obj.put("name", "jagadeesh_updated_patchmethod");
		obj.put("job", "QA Lead_updated_patch_method");
		payload = obj.toString();

		System.out.println(payload);


		Response res = Userimpl.updateUserByPatchMethod(payload);


		boolean statusvalidate = RestUtil.validateStatusCode200(res);

		logger.info(res.getStatusCode());

		// validation of the response
		String jsonString = res.asString();	
		// use https://jsonpathfinder.com/ for finding the json path
		username = JsonPath.from(jsonString).get("name");
		userJob =JsonPath.from(jsonString).getString("job");

		validateUser.UserValidator(obj, username, userJob);

		logger.info("User updated using patch method succcessfully");
    	
    	
    }

	@Test
    @Given("^delete user info api$")
    public void delete_user_info_api()  {
       
    	Response res = Userimpl.deleteUserInfo();
    	boolean statusvalidate = RestUtil.validateStatusCode204(res);
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}


