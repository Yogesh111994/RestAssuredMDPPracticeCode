package com.wiremok;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class GetMockUserTest {

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
	public void mockUserApiTest() {
		ApiMocks.createUser();
		RestAssured.given()
		.queryParam("param", "value")
		               .when()
		               .get("/api/users")
		               .then().log().all()
		               .statusCode(200)
		               .body("name", equalTo("yogeshQuerry"));
		
		               
	}
}
