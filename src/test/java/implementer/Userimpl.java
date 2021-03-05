package implementer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.sun.media.jfxmedia.logging.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Userimpl {

	public static String API = "/api/users";

	/*
	 * payload = name, job id
	 */

	
	
	public static Response createuser(String payload) {

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

	public static Response getsingleuserinfo() {

		String apiurl2= "/api/users/2";

		Response res = 
				given().
				header("content-type","application/json").
				accept(ContentType.JSON).
				when().
				get(apiurl2);

		return res;
	}

	public static Response updateUserByPutMethod(String payload) {
		
		String apiurl2= API+"/2";

		Response res = 
				given().
				header("content-type","application/json").
				accept(ContentType.JSON).
				body(payload).
				when().
				put(apiurl2);

		return res;
		
	}

	public static Response updateUserByPatchMethod(String payload) {
		String apiurl2= API+"/2";

		Response res = 
				given().
				header("content-type","application/json").
				accept(ContentType.JSON).
				body(payload).
				when().
				patch(apiurl2);

		return res;
	}

	public static Response deleteUserInfo() {
		String apiurl2= API+"/2";

		Response res = 
				given().
				header("content-type","application/json").
				accept(ContentType.JSON).
				when().
				delete(apiurl2);

		return res;
	}

	




}
