package postAPIRequest;

import _POJO.Booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

// POJO- Plain old java Object
//Can not extends any other class
// oops- encapsulation
// private class vars -- json body
// public setter/getter

// serialization --> Java object to other format : file/media/json/xml/test
// pojo  json --> serialization
// json pojo --> Deserialization
// add Jackson bide dependancy for the serialization

public class BookingAuthWithPojoTest {

	@Test
	public void getBookingAuthTestJsonTest() {
		
		Booking booking = new Booking("admin","password123");
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String tokenId = given().log().all()
		.contentType(ContentType.JSON)
		.body(booking)
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract().path("token");
		System.out.println("Token id: "+tokenId);
        
	}
	
}
