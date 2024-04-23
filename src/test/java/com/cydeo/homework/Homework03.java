package com.cydeo.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Homework03 {
    String url ="https://www.zippopotam.us";
    /**

 Link —-> https://www.zippopotam.us/#

     */


/**
    TASK 1
    Given Accept application/json
    And path zipcode is 22031
    When I send a GET request to /us endpoint
    Then status code must be 200
    And content type must be application/json
    And Server header is cloudflare
    And Report-To header exists
    And body should contains following information
    post code is 22031
    country is United States
    country abbreviation is US
    place name is Fairfax state is Virginia
 */

@Test
public void task1(){

    Response response = RestAssured.given()
            .accept(ContentType.JSON) //hey api please send me json response
            .when()
            .get(url);

    response.prettyPrint();


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
