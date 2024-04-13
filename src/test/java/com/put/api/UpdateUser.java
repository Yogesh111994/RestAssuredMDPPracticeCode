package com.put.api;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUser {

	public class CreateUserTestWithLombok {

		public String getRandomEmailId() {
			return "apiAutomation" + System.currentTimeMillis() + "@gmail.com";
		}

		
		@Test
		public void createTheUser() {
			// Create the user	
			RestAssured.baseURI = "https://gorest.co.in";

			User user = new User("yogesh", getRandomEmailId(), "male", "active");

			Response response = RestAssured.given().log().all()
					.contentType(ContentType.JSON)
					.header("Authorization", "Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
					.body(user).when().log().all()
					.post("/public/v2/users");
			int userId=response.jsonPath().get("id");
			System.out.println(userId);
			user.setName("yogesh automation");
			user.setStatus("inactive");
			RestAssured.baseURI = "https://gorest.co.in";
				 RestAssured.given().log().all()
					.contentType(ContentType.JSON)
					.header("Authorization", "Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
					.body(user).when().log().all()
					.put("/public/v2/users/"+userId)
					.then()
					.assertThat()
					.statusCode(200)
					.and()
					.body("name", equalTo(user.getName()))
					.and()
					.body("id", equalTo(userId))
					.and()
					.body("status", equalTo(user.getStatus()));	
		}
	}
}
