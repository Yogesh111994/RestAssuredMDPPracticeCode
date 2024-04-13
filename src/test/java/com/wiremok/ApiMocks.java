package com.wiremok;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.client.WireMock;

public class ApiMocks {

	public static void  createUser() {
		// Create stub for getUser
		stubFor(get(urlEqualTo("/api/users"))
				              .willReturn(aResponse()
				              .withStatus(200)
				              .withHeader("Content-Type", "Application/json")
				              .withBody("{\r\n"
				              		+ "  \"name\": \"yogesh\"\r\n"
				              		+ "}")));
		                           
	}
	
	public static void  createDummyUserWithQueryParameter() {
		// Create stub for getUser
		stubFor(get(urlPathEqualTo("/api/users"))
				.withQueryParam("param", equalTo("value"))
				              .willReturn(aResponse()
				              .withStatus(200)
				              .withHeader("Content-Type", "Application/json")
				              .withBody("{\r\n"
				              		+ "  \"name\": \"yogeshQuerry\"\r\n"
				              		+ "}")));
		                           
	}
	
	public static void  createDummyUser() {
		// Create stub for getUser
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", WireMock.equalTo("application/json"))
				.withRequestBody(equalToJson("{\"name\": \"yogesh\"}"))
				.willReturn(aResponse()
				              .withStatus(201)
				              .withHeader("Content-Type", "Application/json")
				              .withBody("{\"message\": \"User is created\"}")));
		                           
	}
	
	public static void deleteDummyUser() {
		
		stubFor(delete(urlEqualTo("/api/users"))
										.willReturn(aResponse()
				              .withStatus(204)
				              .withHeader("Content-Type", "Application/json")
				              .withBody("{\"message\": \"User is Deleted\"}")));
	}
}
