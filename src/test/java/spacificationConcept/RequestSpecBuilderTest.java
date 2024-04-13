package spacificationConcept;

import io.restassured.RestAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static  org.hamcrest.Matchers.*;
import  static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class RequestSpecBuilderTest {

	
	@Test
	public  RequestSpecification  User_Req_Spec() {
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification requestSpec = new RequestSpecBuilder()
		.setBaseUri("https://gorest.co.in")
		.setContentType(ContentType.JSON)
		.addHeader("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.build();
		return requestSpec;
		}
	
	@Test
	public void getUser_With_Request_Spec() {
		given().log().all()
		             .spec(User_Req_Spec())
		             .get("/public/v2/users")
		.then().log().all()
		             .statusCode(200);
		
	}
	
	@Test
	public void getUser_With_param_Request_Spec() {
		given().log().all()
		.queryParam("name","naveen")
		.queryParam("gender", "male")
		.spec(User_Req_Spec())
		.get("/public/v2/users")
		.then().log().all()
		.statusCode(200);
		
	}
	
}
