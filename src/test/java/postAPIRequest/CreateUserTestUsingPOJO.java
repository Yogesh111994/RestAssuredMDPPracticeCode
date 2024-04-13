package postAPIRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import _POJO.CreateUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
public class CreateUserTestUsingPOJO {

	
	public static  String getRandomEmailId() {
		return "automation"+System.currentTimeMillis()+"@gmail.com";
	}
	@Test
	public void createUserTest() {
		
		System.out.println(getRandomEmailId());
		// Direct supply the json string
		// pass the json file
		// pojo -java object to json with help of jackson bind lib
		System.out.println();
		RestAssured.baseURI="https://gorest.co.in";
		// need to add Jackson bind library
		CreateUser user = new CreateUser("yogesh","male",getRandomEmailId(),"active");
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.body(user)
		.when().log().all()
		.post("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.body("name", equalTo(user.getName()))
		.body("status", equalTo(user.getStatus()));
		
		
		
	
		
		
	}
}
