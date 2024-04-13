package spacificationConcept;

import static  org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import  static io.restassured.RestAssured.*;
public class RequestResponseSpecTest {

	public static  RequestSpecification  User_Req_Spec() {
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification requestSpec = new RequestSpecBuilder()
		.setBaseUri("https://gorest.co.in")
		.setContentType(ContentType.JSON)
		.addHeader("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.build();
		return requestSpec;
		}
	
	public static  ResponseSpecification get_Res_Spec_200_Ok() {
		   ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
		   .expectContentType(ContentType.JSON)
		   .expectStatusCode(200)
		   .expectHeader("Server", "cloudflare")
		   .build();
		   return res_spec_200_ok;
	}
	
	@Test
	public void getUser_req_resp_Test() {
		given().log().all()
		.spec(User_Req_Spec())
		.get("/public/v2/users")
		.then()
		.assertThat()
		.spec(get_Res_Spec_200_Ok());
	}
}
