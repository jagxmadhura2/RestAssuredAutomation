package implementer;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Registerimpl {
	public static String API = "/api/register";
	
	public static Response registerUser(String payload) {
		String apiurl= API;

		Response res = 
				given().
				header("content-type","application/json").
				accept(ContentType.JSON).
				body(payload).
				when().
				post(apiurl);

		return res;
	}
	
	
	
}
