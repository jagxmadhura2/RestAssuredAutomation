package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class SampleApi {

	
	@Test
	public void test_1_getsingleuserinfo() {
		
		Response res =  get("https://reqres.in/api/users/2");
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getTime());
		System.out.println(res.getBody().asString());
		System.out.println(res.getHeader("content-type"));
		
	//	Integer statuscode = res.getStatusCode();
	
		
	}
	
	
	@Test
	public void test_2_userinfo() {
		
		baseURI = "https://reqres.in/api";
		
	given().
			get("users?page=2").
		then().
			statusCode(200).
			body("data[0].email", equalTo("michael.lawson@reqres.in")).
			body("data[1].id", equalTo(8)).
			log().all();
		
	}
	
	
	
	
}
