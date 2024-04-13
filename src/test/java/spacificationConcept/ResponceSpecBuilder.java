package spacificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static  org.hamcrest.Matchers.*;
import  static io.restassured.RestAssured.*;

public class ResponceSpecBuilder {

	
	@Test
	public static  ResponseSpecification get_Res_Spec_200_Ok() {
		   ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
		   .expectContentType(ContentType.JSON)
		   .expectStatusCode(200)
		   .expectHeader("Server", "cloudflare")
		   .build();
		   return res_spec_200_ok;
	}
	
	public static ResponseSpecification get_Resp_Spec_200_Ok_withBody() {
		ResponseSpecification resp_spec_200_ok_withBody = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
		.expectBody("$.size()",equalTo(10))
		.expectBody("id",hasSize(10))
		.build();
		
		return resp_spec_200_ok_withBody;
	}
	
	public static ResponseSpecification getResp_spec_with_401_authFail() {
		 ResponseSpecification res_spec_401_auth_fail = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(401)
			.expectHeader("Server", "cloudflare")
			.build();
		 return res_spec_401_auth_fail;
	}
	
	@Test
	public void get_user_resp_spec_test_200() {
		
		RestAssured.baseURI="https://gorest.co.in";
		given().log().all()
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(get_Res_Spec_200_Ok())
		.spec(get_Resp_Spec_200_Ok_withBody());
		
	}
	
	@Test
	public void getUser_resp_spec_401_Auth_Fail_Test() {
		RestAssured.baseURI="https://gorest.co.in";
		given().log().all()
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d796742133")
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(getResp_spec_with_401_authFail());
	}
	
}
