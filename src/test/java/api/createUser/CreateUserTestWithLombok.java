package api.createUser;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUserTestWithLombok {

	public String getRandomEmailId() {
		 
		return "apiAutomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void  CreateUserTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		UserPOJOClass user = new UserPOJOClass("yogesh",getRandomEmailId(),"male","active");
		Response response=RestAssured.given().given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.body(user)
		.when()
		.post("/public/v2/users");
		JsonPath js = response.jsonPath();
		Object userId=js.get("id");
		System.out.println(userId);
		
		// Get API to get same user
		
		Response getResponse=RestAssured.given().given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.body(user)
		.when().log().all()
		.get("/public/v2/users"+userId);
		
		
		// de-serilalization
		
		ObjectMapper mapper =  new ObjectMapper();
	try {
		UserPOJOClass userResponse =mapper.readValue(getResponse.getBody().asString(), UserPOJOClass.class);
      System.out.println(userResponse.getId()+" : "+userResponse.getName()+" : "+ userResponse.getEmail()+" : "+userResponse.getStatus());
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	}

	@Test
public void  CreateUserWith_BuilderPatternTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		//
				
		Response response=RestAssured.given().given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
//		.body(user)
		.when()
		.post("/public/v2/users");
		JsonPath js = response.jsonPath();
		Object userId=js.get("id");
		System.out.println(userId);
		
		// Get API to get same user
		
		Response getResponse=RestAssured.given().given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
	//	.body(user)
		.when().log().all()
		.get("/public/v2/users"+userId);
		
		
		// de-serilalization
		
//		ObjectMapper mapper =  new ObjectMapper();
//		try {
//			UserPOJOClass userResp = mapper.readValue(getResponse.getBody().asString(), UserPOJOClass.class);
//			
//			System.out.println(userResp.getId());
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//						}
	}
}
