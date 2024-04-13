package com.wiremok;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class MockPostCreateUser {

	@BeforeMethod
	public void SetUp() {
		WiremockSetup.startWireMockServer();
		RestAssured.baseURI ="http://localhost";
		RestAssured.port=8085;
	}
	@AfterMethod
	public void tearDown() {
		WiremockSetup.stopWireMockServer();
	}
	
	@Test
	public void createDummyUserTest() {
		ApiMocks.createDummyUser();
		RestAssured.given().log().all()
		.contentType("application/json")
		.body("{\"name\": \"yogesh\"}")
		.when()
		.post("/api/users")
		.then()
		.assertThat()
		.statusCode(201)
		.body("message", equalTo("User is created"));
		
		
	}
}
