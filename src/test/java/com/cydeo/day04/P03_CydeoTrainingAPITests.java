package com.cydeo.day04;

import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {

      /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
    */

    @DisplayName("GET /student/2")
    @Test
    public void test1(){

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 289)
                .when().get("/student/{id}");

        // NOTE -> In recordings there was student with ID 2
        // It is deleted. New student with same info generated and ID
        // for this student is 289

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json;charset=UTF-8",response.contentType());

        //And Date header is exist
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //And Server header is envoy
        assertEquals("envoy",response.header("server"));

//        firstName Mark              -->students[0].firstName
//        batch 13                    -->students[0].batch
//        major math                  -->students[0].major
//        emailAddress mark@email.com -->students[0].contact.emailAddress
//        companyName Cydeo           -->students[0].company.companyName
//        street 777 5th Ave          -->students[0].company.address.street
//        zipCode 33222               -->students[0].company.address.zipCode

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Mark",jsonPath.getString("students[0].firstName"));

        assertEquals(13,jsonPath.getInt("students[0].batch"));

        assertEquals("math",jsonPath.getString("students[0].major"));

        assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));

        assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));

        assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));

        assertEquals(33222,jsonPath.getInt("students[0].company.address.zipCode"));


    }

           /*

    TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
     */

    @DisplayName("GET /student/2")
    @Test
    public void homework(){

        Response response = given()
                .accept(ContentType.JSON)
                .and().pathParam("batch", 22)
                .when()
                .get("/student/batch/{batch}");

        JsonPath jsonPath = response.jsonPath();

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json;charset=UTF-8",response.contentType());

        //And Date header is exist
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //And Server header is envoy
        assertEquals("envoy",response.header("server"));

        List<Integer> batchNumbers = jsonPath.getList("students.batch");

        for (Integer batchNumber : batchNumbers) {
            assertEquals(22,batchNumber);
        }

    }



}
