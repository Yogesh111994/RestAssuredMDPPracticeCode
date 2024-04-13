package com.delete.api;


import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteApi {
// create the user
// Take the id of created user and delete that
//  validate that the entry got deleted using get call
	
	public String getRandomEmailId() {
		return "apiAutomation" + System.currentTimeMillis() + "@gmail.com";
	}	
	
	
	@Test
	public void deleteTheUser() {
		 // create the user
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("yogesh", getRandomEmailId(), "male", "active");

		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
				.body(user).when().log().all()
				.post("/public/v2/users");
		int userId=response.jsonPath().get("id");
		System.out.println(userId);
		
		// Delete the user
		
		RestAssured.given().log().all()
		.header("Authorization", "Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.when()
		.delete("/public/v2/users/"+userId)
		.then().log().all()
		.assertThat()
		.statusCode(204);
		
		
		// get the user
		RestAssured.given().log().all()
		.header("Authorization", "Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.when().log().all()
		.get("/public/v2/users/"+userId)
		.then().log().all()
		.assertThat()
		.statusCode(404)
		.and()
		.body("message", equalTo("Resource not found"));
	}
	
}
