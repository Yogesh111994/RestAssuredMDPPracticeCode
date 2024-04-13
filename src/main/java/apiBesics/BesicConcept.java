package apiBesics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload.AddPlace;
public class BesicConcept {

	public static void main(String[] args) {
		
		// Validate if add place is working expected
		// given-all input detail
		// when- Submit the API - resource and HTTP method
		// then- validate the response
		
		// Add Place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(AddPlace.payload()).post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(responce);

	
		JsonPath js= new JsonPath(responce);
		String place_id = js.getString("place_id");
		System.out.println("Place Id  : "+place_id);
		
		// Update Place
		String newAddress = "70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Get Place
		
		String getPlaceResponce = given().log().all().queryParam("key", "qaclick123").queryParam("place_id",place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println("getPlaceResponce====="+getPlaceResponce);
		
		JsonPath js1 = new JsonPath(getPlaceResponce);
		String actualAddress =js1.getString("address");
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
	}

}
