package getAPIRequest;
import static  io.restassured.RestAssured.*;
import  static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetAPIWithPathParam {

	// query vs path
	//<K,V> 
	//?
	@Test
	public void getCirciutDataAPI_WithYearTest() {
		RestAssured.baseURI="https://ergast.com";
		given().log().all()
		.pathParam("year", 2020)
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.contentType("application/json; charset=utf-8")
		.body("MRData.CircuitTable.season", equalTo("2020"))
		.and()
		.body("MRData.CircuitTable.season", hasSize(20));
	}
	
	@DataProvider
	public Object[][] getCircuitYearData() {
		
		return new Object[][] {
			{"2016", 21},
			{"2017", 20},
			{"2018", 21},
			{"2019",21}
		};
	}
	@Test(dataProvider="getCircuitYearData")
	public void getCirciutDataAPI_WithYearTest(String SeasonYear,int TotalCircuit) {
		RestAssured.baseURI="https://ergast.com";
		given().log().all()
		.pathParam("year", SeasonYear)
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.contentType("application/json; charset=utf-8")
		.body("MRData.CircuitTable.season", equalTo(SeasonYear));
//		.and()
//		.body("MRData.total", equalTo(TotalCircuit));
	}
	
}








