package com.cydeo.homework.homework01;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class T1_HR_countries_US extends HrTestBase {

/*
Task 1 :
- Given accept type is Json
- When users sends request to /countries/US
- Then status code is 200
- And Content - Type is application/json
- And response contains United States of America
*/

    @DisplayName("GET Request to /countries/US")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("countries/US");

        //response.prettyPrint();

        //Then status code is 200
        assertEquals(200, response.getStatusCode());

        //- And Content - Type is application/json
        assertEquals("application/json", response.contentType());

        //- And response contains United States of America
        assertTrue(response.body().asString().contains("United States of America"));

    }


    /*
    Task 2 :
    - Given accept type is Json
    - When users sends request to /employees/1
    - Then status code is 404
    */
    @DisplayName("GET Request to /employees/1")
    @Test
    public void test2() {

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/employees/1");

        //response.prettyPrint();

        assertEquals(404, response.getStatusCode());

    }


/*
  Task 3 :
- Given accept type is Json
- When users sends request to /regions/1
- Then status code is 200
- And Content - Type is application/json
- And response contains Europe
- And header should contains Date
- And Transfer-Encoding should be chunked
*/
@DisplayName("GET Request to /regions/1")
@Test
public void test3() {

    Response response = given()
            .accept(ContentType.JSON)
            .when()
            .get("/regions/1");
    //- Then status code is 200
    assertEquals(200,response.statusCode());

    //- And Content - Type is application/json
    assertEquals("application/json", response.contentType());

    //- And response contains Europe
    assertTrue(response.body().asString().contains("Europe"));

    //- And header should contains Date
    System.out.println("response.header(\"Date\") = " + response.header("Date"));

    //- And Transfer-Encoding should be chunked
    assertEquals("chunked",response.header("Transfer-Encoding").toString());

}

}
