package validator;

import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

public class UserValidator {

	
	 public void UserValidator(JSONObject JSONResponseBody,String userName,String userJob){
		 SoftAssert softAssert = new SoftAssert(); 
		 softAssert.assertEquals(JSONResponseBody.getString("name"), userName);
		 softAssert.assertEquals(JSONResponseBody.getString("job"), userJob);
		 softAssert.assertAll();	  
	 }

	
	
	
	
	
}
