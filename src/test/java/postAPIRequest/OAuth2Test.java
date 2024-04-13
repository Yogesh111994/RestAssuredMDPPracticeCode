package postAPIRequest;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class OAuth2Test {

	static String tokenId;
	@Test
	public static  void getAccessToken() {
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		 tokenId = given().log().all()
		           .headers("Content-Type","application/x-www-form-urlencoded")
		           .formParam("grant_type", "client_credentials")
		           .formParam("client_id", "ywU9W4ZXI1EGpZyAnAXnA3JpcSbteKon")
		           .formParam("client_secret", "S7bL3hO6wO9r7Ix6")
	  .when()
		           .post("/v1/security/oauth2/token")
	  .then().log().all()
		           .assertThat()
		           .statusCode(200)
		           .extract().path("access_token");
		System.out.println("Token Id"+tokenId);
		
	}
	
	@Test
	public void getInfoTest() {
		RestAssured.baseURI="https://test.api.amadeus.com";
		Response response =given().log().all()
		.header("Authorization","Bearer "+tokenId)
		             .queryParam("origin", "PAR")
		             .queryParam("maxPrice", 200).
		             		when().log().all()
		              .get("/v1/shopping/flight-destinations")
		              .then().log().all()
		              .assertThat()
		              .statusCode(200)
		              .extract().response();
		JsonPath js = response.jsonPath();
		    String total = js.get("data[0].price.total");
		    System.out.println("Total "+total);
	}
}
