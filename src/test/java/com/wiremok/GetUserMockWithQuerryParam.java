package com.wiremok;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetUserMockWithQuerryParam {

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
		public void mockUserApiTestwithQueeryParam() {
			ApiMocks.createDummyUserWithQueryParameter();
			RestAssured.given().log().all()
			.queryParam("param", "value")
			               .when()
			               .get("/api/users")
			               .then().log().all()
			               .statusCode(200)
			               .body("name", equalTo("yogeshQuerry"));
			
			               
		}
}
}
