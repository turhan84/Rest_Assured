package com.cydeo.homework;

import com.cydeo.utilities.ZipCodeTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework03 extends ZipCodeTestBase {

    /**

     Link —-> https://www.zippopotam.us/#

     */


    /**
     * TASK 1
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     * post code is 22031
     * country is United States
     * country abbreviation is US
     * place name is Fairfax state is Virginia
     */
    @DisplayName("Response")
    @Test
    public void task1() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("zipCode", 22031)
                .when()
                .get("/us/{zipCode}");


        //response.prettyPrint();

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.getContentType());

        //And Server header is cloudflare
        assertEquals("cloudflare", response.header("Server"));

        //And Report-To header exists
        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));

        //And body should contains following information
        //post code is 22031
        assertTrue(response.body().asString().contains("22031"));

        //country is United States
        assertTrue(response.body().asString().contains("United States"));

        //country abbreviation is US
        assertTrue(response.body().asString().contains("US"));

        //place name is Fairfax state is Virginia
        assertTrue(response.body().asString().contains("Fairfax"));

        assertTrue(response.body().asString().contains("Virginia"));


    }

    @DisplayName("Response")
    @Test
    public void task1_2() {
        given()
                .log().ifValidationFails()
                .accept(ContentType.JSON)
                .pathParam("zipCode", 22031)
                .when()
                .get("/us/{zipCode}")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .and()
                .header("Server", is("cloudflare"))
                .and()
                .header("Report-To", is(notNullValue()))
                .and()
                .body("'post code'", equalTo("22031"))
                .and()
                .body("country", equalTo("United States"))
                .and()
                .body("'country abbreviation'", equalTo("US"))
                .and()
                .body("places[0].'place name'", equalTo("Fairfax"))
                .and()
                .body("places[0].state", equalTo("Virginia"));


    }


/**
 TASK 2
 Given Accept application/json
 And path zipcode is 50000
 When I send a GET request to /us endpoint
 Then status code must be 404
 And content type must be application/json
 */


/**
 TASK 3
 Given Accept application/json
 And path state is va
 And path city is fairfax
 When I send a GET request to /us endpoint
 Then status code must be 200
 And content type must be application/json
 And payload should contains following information
 country abbreviation is US
 country United States
 place name Fairfax
 each places must contains fairfax as a value each post code must start with 22
 */


}
