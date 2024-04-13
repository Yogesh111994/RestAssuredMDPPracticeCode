package postAPIRequest;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class GoRestAuthTestWithPOJO {

	String rsp="{\r\n"
			+ "    \"username\" : \"admin\",\r\n"
			+ "    \"password\" : \"password123\"\r\n"
			+ "}";
	@Test
	public void getBookingAuthTokenTestWithJsonString() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Response response =given().log().all()
		.contentType(ContentType.JSON)
		.body(rsp)
		.when()
		.post("/auth")
		.then().log().all()
		.extract()
		.response();
		String tokenId=response.path("token");
	System.out.println(tokenId);		
	}
	
	
}
