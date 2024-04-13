package com.wiremok;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteUSerMockAPI {
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
		ApiMocks.deleteDummyUser();
		RestAssured.given().log().all()
		.when()
		.delete("/api/users")
		.then()
		.assertThat()
		.statusCode(204);
		
		
	}
}
