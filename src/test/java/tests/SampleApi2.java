package tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class SampleApi2 {

//	@Test
	public void getalluserinfo() {
		
		baseURI = "https://reqres.in/api";
		
		given().get("/users?page=2").then().statusCode(200).body("data[1].first_name", equalTo("Lindsay")).log().all();
	}
	
	@Test
	public void createuser() {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		
	//	map.put("name", "jagadeesh");
	//	map.put("job", "QA Lead");
		
		System.out.println(map);
		
		
		JSONObject request = new JSONObject();
		
		request.put("name", "jagadeesh");
		request.put("job", "QA Lead");
		
		System.out.println(request.toString());
		
		baseURI = "https://reqres.in/api";
		
		given().
			header("content-type","application/json").
			accept(ContentType.JSON).
			body(request.toString()).
		when().
			post("/users").
		then().
			statusCode(201).
		log().all();
		
	}
	
}
