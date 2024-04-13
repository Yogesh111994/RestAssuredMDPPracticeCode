package jaywayPathValidator;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class JsonPathTest {

	@Test
	public void getCountryValue() {

		String base_url ="http://ergast.com/api/f1";
		String year ="2017";
		String url = base_url+"/"+year+"/"+"circuits.json";

		System.out.println(url);
		// Send the get request and extract the response
		Response response =given().get(url);

		// Extract response body as string
		String jsonResponse =response.getBody().asString();
		System.out.println(jsonResponse);
		// use json path to extract the country value using the given json path expression

		List<String> countryList = JsonPath.read(jsonResponse, "$..CircuitTable.Circuits[*].Location.country");
		System.out.println("country List"+countryList);
		Object total=JsonPath.read(jsonResponse, "$..total");
		System.out.println(total);

	}
	@Test
	public void getProductTest() {

		RestAssured.baseURI="https://fakestoreapi.com";

		Response response =given().log().all().when().log().all().get("/products");
		String jsonResponse =response.asString();
		System.out.println(jsonResponse);
		List<Float> ratingLlist =JsonPath.read( jsonResponse,"$[?(@.rating.rate>3)].rating.rate");
		System.out.println(ratingLlist);

	}

	@Test
	public void getJewelery() {

		RestAssured.baseURI="https://fakestoreapi.com";
		Response response =given().log().all().when().log().all().get("/products");
		String jsonResponse =response.asString();
		System.out.println(jsonResponse);
		List<Map<String,Object>>  info =JsonPath.read( jsonResponse,"$[?(@.category=='jewelery')].[\"title\",\"price\"]");

		for(Map<String,Object> product : info) {
			String prod= (String)product.get("title");
			Object price =(Object)product.get("price");

			System.out.println("Title of product : "+prod);
			System.out.println("price of product : "+ price);
			System.out.println("-------------");


		}


	}
	
	@Test
	public void  getProcutcListWithThreeElement() {

		RestAssured.baseURI="https://fakestoreapi.com";
		Response response =given().log().all().when().log().all().get("/products");
		String jsonResponse =response.asString();
		System.out.println(jsonResponse);
		List<Map<String,Object>>  info =JsonPath.read( jsonResponse,"$[?(@.category=='jewelery')].[\"title\",\"price\",\"id\"]");

		for(Map<String,Object> product : info) {
			String prod= (String)product.get("title");
			Object price =(Object)product.get("price");
			Integer id=  (Integer)product.get("id");

			System.out.println("Title of product : "+prod);
			System.out.println("price of product : "+ price);
			System.out.println("id of product : "+ id);

			System.out.println("-------------");

		}

	}
}