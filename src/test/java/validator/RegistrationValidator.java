package validator;

import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

public class RegistrationValidator {

	
	public void RegistrationValidator(JSONObject JSONResponseBody, Integer id, String token) {
		SoftAssert softAssert = new SoftAssert(); 
		 softAssert.assertEquals(JSONResponseBody.getString("id"), id);
		 softAssert.assertEquals(JSONResponseBody.getString("token"), token);
		 softAssert.assertAll();
		
	}
	
	
}
