package getAPIRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPIRequestWithoutBDD {

	
	@Test
	public void getAllUserApiTest() {
		
		RestAssured.baseURI= "https://gorest.co.in";
		RequestSpecification Request = RestAssured.given();
		Request.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674");
		 Response response = Request.get("/public/v2/users");
		 
		 // Get Status Code
		 int statusCode =response.statusCode();
		 System.out.println("Status Code : "+statusCode);
		 Assert.assertEquals(statusCode, 200);
		 
		 //  GET response Message
		 String responseMessage = response.getStatusLine();
		 System.out.println("Responce Message : "+responseMessage);
		 
		 // Fetch body
		 response.prettyPrint();
		
		 // fetch header
		 
		String header = response.header("Content-Type");
		System.out.println(header);
		
		System.out.println("==========================");
		// Fetch all header
		
		 List<Header> headerList = response.headers().asList();
		 
		 for(Header h: headerList) {
			 System.out.println(h.getName()+" : "+h.getValue());
		 }
	}
	
	@Test
	public void getAllUserWithQueryParameterAPITest() {
		RestAssured.baseURI= "https://gorest.co.in";
		RequestSpecification Request = RestAssured.given();
		Request.queryParam("name", "Bodhan Johar");
		Request.queryParam("gender", "female");
		Request.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674");
		 Response response = Request.get("/public/v2/users");
		 
		// Get Status Code
				 int statusCode =response.statusCode();
				 System.out.println("Status Code : "+statusCode);
				 Assert.assertEquals(statusCode, 200);
				 
				 //  GET response Message
				 String responseMessage = response.getStatusLine();
				 System.out.println("Responce Message : "+responseMessage);
				 
				 // Fetch body
				 response.prettyPrint();
	}
	@Test
	public void getAllUserWithQueryParameterWithHashMapAPITest() {
		RestAssured.baseURI= "https://gorest.co.in";
		RequestSpecification Request = RestAssured.given();
		Map<String,String> queryParamsmap=new HashMap<String,String>();
		queryParamsmap.put("gender", "female");
		queryParamsmap.put("status", "active");
		Request.queryParams(queryParamsmap);
		Request.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674");
		 Response response = Request.get("/public/v2/users");
		 
		 int statusCode =response.statusCode();
		 System.out.println("Status Code : "+statusCode);
		 Assert.assertEquals(statusCode, 200);
		 
		 //  GET response Message
		 String responseMessage = response.getStatusLine();
		 System.out.println("Responce Message : "+responseMessage);
		 
		 // Fetch body
		 response.prettyPrint();
		
	}
}
