package getAPIRequest;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
public class GetAPIWithBDD {

	private Response response;

	@Test
	public void getProductApiTest() {
		given().log().all()
		.when().log().all()
		.get("https://gorest.co.in/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType("application/json; charset=utf-8")
		.and()
		.header("Connection", "keep-alive")
		.and()
		.body("id", is(notNullValue()))
		. and()
		.body("name",hasItem( "Prof. Baalaaditya Dwivedi"))
		.and()
		.body("$.size()",equalTo(10));

	}
	@Test
	public void getRestApiWithQueryParameter() {

		given().log().all()
		.queryParam("status","active")
		.queryParam("gender", "male")
		.when().log().all()
		.get("https://gorest.co.in/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType("application/json; charset=utf-8");

	}

	//  Extract specific value from array
	@Test
	public void getGoRestDataAPI_With_Extract_Body_() {
		Response response =given().log().all()
				.when()
				.get("https://gorest.co.in/public/v2/users");

		JsonPath js = response.jsonPath();

		int userId = js.getInt("[0].id");
		System.out.println("first user id : "+userId);

		String  name = js.getString("[0].name");
		System.out.println("first user name : "+name);

		String  email = js.getString("[0].email");
		System.out.println("first user email : "+email);

		String  gender = js.getString("[0].gender");
		System.out.println("first user gender : "+gender);

	}


	//  Extract all value from json array
	@Test
	public void getGoRestDataAPI_With_Extract_Body_fromArray() {
		Response response =given().log().all()
				.when()
				.get("https://gorest.co.in/public/v2/users");

		JsonPath js = response.jsonPath();

		List<Integer> UserIds = js.getList("id");
		List <String>  names = js.getList("name");
		List<String>  emails = js.getList("email");
		List<String>  genders = js.getList("email");

		for(int i=0; i < UserIds.size();i++) {
			int id =UserIds.get(i);
			String name =names.get(i);
			String email =emails.get(i);
			String gender = genders.get(i);

			System.out.println("User ID : "+id+"  "+"Name : "+name+"  "+"email : "+email+"  "+"Gender : "+gender);
		}

	}

	// By creating response object we can get the value from JSON
	@Test
	public void GetUserAPI_With_Extract_Body_Json() {

		Response response = given().log().all()
				.queryParam("id",5782483)
				.when().log().all()
				.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
				.get("https://gorest.co.in/public/v2/users/5782483");

		JsonPath js =response.jsonPath();
		System.out.println(js.getInt("id"));
		System.out.println(js.getString("name"));
	}

	@Test
	public void getUserAPI_With_Extract_Body_WithJson_extect() {

//		Object userId =given().log().all()
//		.queryParam("id",5782438)
//		.when().log().all()
//		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
//		.get("https://gorest.co.in/public/v2/users")
//		.then().log().all()
//		.extract().path("id");
//		
//		System.out.println(userId);
		
		Response response = given().log().all()
		.queryParam("id",5782438)
		.when()
		.header("Authorization","Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
		.get("https://gorest.co.in/public/v2/users")
		.then().log().all()
		.extract()
		.response();

		Object id =response.path("id");
		Object name =response.path("name");
		
		System.out.println(id);
		System.out.println(name);
	}
}












