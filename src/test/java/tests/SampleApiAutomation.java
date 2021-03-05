package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SampleApiAutomation {


	//REGISTER-SUCCESSFUL, CREATE

	@Test
	public void test1_createuser() {

		//	Map<String, Object> map = new HashMap<String,Object>();
		//map.put("name", "jagadeesh");
		//map.put("job", "QA Lead");

		//System.out.println(map);


		JSONObject request = new JSONObject();

		request.put("name", "jagadeesh");
		request.put("job", "QA Lead");

		System.out.println(request.toString());
		baseURI = "https://reqres.in";
		given().
		header("content-type","application/json").
		accept(ContentType.JSON).
		body(request.toString()).
		when().
		post("/api/users").
		then().
		statusCode(201).
		log().all();

		
		
		Response res = given().
				header("content-type","application/json").
				accept(ContentType.JSON).
				body(request.toString()).
				when().
				post("/api/users");
		
		
		
	}


	//SINGLE USER, 

	@Test
	public void test2a_singleuser() {

	//	baseURI = "https://reqres.in";

		given().
		get("/api/users/2").
		then().statusCode(200).
		body("data.id", equalTo(2)).
		body("data.email", equalTo("janet.weaver@reqres.in")).
		body("data.first_name", equalTo("Janet")).
		body("data.last_name", equalTo("Weaver")).
		log().all();
	}

	//LIST USERS

	@Test
	public void test_2b_listusers() {

		baseURI = "https://reqres.in";

		given().
		get("/api/users?page=2").
		then().
		statusCode(200).
		body("data[0].email", equalTo("michael.lawson@reqres.in")).
		body("data[1].id", equalTo(8)).
		log().all();



	}

	//UPDATE
	@Test
	public void test_3a_updateapi() {
		
		JSONObject request = new JSONObject();

		request.put("name", "jagadeesh_edited");
		request.put("job", "QA Lead_edited");

		System.out.println(request.toString());
		baseURI = "https://reqres.in";
		given().
		header("content-type","application/json").
		accept(ContentType.JSON).
		body(request.toString()).
		when().
		put("/api/users/2").
		then().
		statusCode(200).
		log().all();
		
	}
	
	//using patch api update
	@Test
	public void test_3b_updateapi() {
		
		JSONObject request = new JSONObject();

		request.put("name", "jagadeesh_edited");
		request.put("job", "QA Lead_edited");

		System.out.println(request.toString());
		baseURI = "https://reqres.in";
		given().
		header("content-type","application/json").
		accept(ContentType.JSON).
		body(request.toString()).
		when().
		patch("/api/users/2").
		then().
		statusCode(200).
		log().all();
		
	}
	
	
	
	//DELETE
	@Test
	public void test_4_deleteapi() {
		
		baseURI = "https://reqres.in";

		given().
		delete("/api/users/2").
		then().statusCode(204).
		log().all();
		
		
		
	}
	
	
	


}
