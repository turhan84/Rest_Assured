package com.cydeo.homework;

import com.cydeo.pojo.homework.Place;
import com.cydeo.pojo.homework.State;
import com.cydeo.utilities.ZipCodeTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework03 extends ZipCodeTestBase {

    /**
     * TASK 1
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us/{zipcode} endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     * post code is 22031
     * country is United States
     * country abbreviation is US
     * place name is Fairfax
     * state is Virginia
     */

    @Test
    public void task1() {

        Response response = RestAssured.given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("zipCode", 22031).
                when().get("/us/{zipCode}"); // baseURI+ENDPOINT

        response.prettyPrint();

        //     * Then status code must be 200
        assertEquals(200, response.statusCode());

        //     * And content type must be application/json
        assertEquals(ContentType.JSON.toString(), response.contentType());

        //     * And Server header is cloudflare
        assertEquals("cloudflare", response.header("Server"));

        //     * And Report-To header exists
        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));


        JsonPath jp = response.jsonPath();

        //     * And body should contains following information
        //     * post code is 22031
        String postCode = jp.getString("'post code'");
        System.out.println("postCode = " + postCode);
        assertEquals("22031", postCode);

        //     * country is United States
        assertEquals("United States", jp.getString("country"));

        //     * country abbreviation is US
        assertEquals("US", jp.getString("'country abbreviation'"));

        //     * place name is Fairfax
        assertEquals("Fairfax", jp.getString("places[0].'place name'"));

        //     * state is Virginia
        assertEquals("Virginia", jp.getString("places[0].state"));

    }

    @Test
    public void task1HamCrest() {
        RestAssured.given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("zipCode", 22031).
                when().get("/us/{zipCode}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server", is("cloudflare"))
                .header("Report-To",notNullValue())
                .body("'post code'",is("22031"))
                .body("country",is("United States"))
                .body("'country abbreviation'",is("US"))
                .body("places[0].'place name'",is("Fairfax"))
                .body("places[0].state",is("Virginia"));


    }
    /**
     * TASK 2
     * Given Accept application/json
     * And path zipcode is 50000
     * When I send a GET request to /us/{postCode} endpoint
     * Then status code must be 404
     * <p>
     * 404 --> Not Found
     * NEGATIVE --> Intentional wrong data to get expected values
     * --> 404
     * --> {
     * "message":"Data is not exist",
     * "status" :404
     * }
     */
    @Test
    public void task2() {

        RestAssured.given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("zipCode", 50000).
                when().get("/us/{zipCode}").prettyPeek().
                then().statusCode(404);

    }

    /**
     * TASK 3
     *     Given Accept application/json
     *     And path state is va
     *     And path city is Fairfax
     *     When I send a GET request to /us/{state}/{city} endpoint
     *     Then status code must be 200
     *     And content type must be application/json
     *     And payload should contains following information
     *     country abbreviation is US
     *     country United States
     *     place name Fairfax
     *     each places must contains fairfax as a value
     *     each post code must start with 22
     */

    @Test
    public void task3POJO() {

        JsonPath jp = RestAssured.given()
                .accept(ContentType.JSON)
                .pathParams(getStateAndCity("va","Fairfax"))
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        State state = jp.getObject("", State.class);
        System.out.println("state = " + state);


        //     *     country abbreviation is US
        String countryAbbreviation = state.getCountryAbbreviation();
        assertEquals("US",countryAbbreviation);

        //     *     country United States
        String country = state.getCountry();
        assertEquals("United States",country);

        //     *     place name Fairfax
        String placeName = state.getPlaceName();
        assertEquals("Fairfax",placeName);


        assertAll("Learning SOFT ASSERT in JUNIT5",

                ()->assertEquals("US",countryAbbreviation),
                ()->assertEquals("United States",country),
                ()->assertEquals("Fairfax",placeName)
        );

        // Streams ?

        //     *     each places must contains fairfax as a value
        //     *     each post code must start with 22

        List<Place> allPlaces = state.getAllPlaces();

        for (Place eachPlace : allPlaces) {
            assertTrue(eachPlace.getPostCode().startsWith("22"));
            assertTrue(eachPlace.getPlaceName().contains("Fairfax"));
        }


    }

    public static Map<String,String> getStateAndCity(String state,String city){

        Map<String,String> pathParams=new HashMap<>();
        pathParams.put("state", state);
        pathParams.put("city", city);

        return pathParams;
    }


}
