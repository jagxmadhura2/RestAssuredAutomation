package util;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import io.restassured.response.Response;

public class RestUtil {

	public static String userName = "username";
	public static String password = "password";
	//public static String password = Configure.getValue("PASSWORD");
	public static RequestSpecBuilder builder = new RequestSpecBuilder();
	public static RequestSpecification requestSpec = null;
	
	
	
	public static boolean validateStatusCode200(Response response){
		CustomListener.sampleresponse(response.asString());
		response.then().assertThat().statusCode(200);
		return true;
	}
	
	
	public static boolean validateStatusCode201(Response response){
		CustomListener.sampleresponse(response.asString());
		response.then().assertThat().statusCode(201);
		return true;
	}
	
	public static boolean validateStatusCode401(Response response){
		CustomListener.sampleresponse(response.asString());
		response.then().assertThat().statusCode(401);
		return true;
	}
	
	
	public static boolean validateStatusCode404(Response response) {
		CustomListener.sampleresponse(response.asString());
		response.then().assertThat().statusCode(404);
		return true;
	}
	
	public static boolean validateStatusCode204(Response response) {
		CustomListener.sampleresponse(response.asString());
		response.then().assertThat().statusCode(204);
		return true;
	}
	
	
	public static String getPayload(String fname) {
		String fpath = System.getProperty("user.dir")+"/src/test/resources/com/thed/demoapitesting/";
		fpath += fname;
		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(fpath));
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		//System.out.println("File Content: \n" + jsonData);
		CustomListener.samplerequest(jsonData);
		return jsonData;
	}


	


	
	
	
}
