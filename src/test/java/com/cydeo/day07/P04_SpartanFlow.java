package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P04_SpartanFlow extends SpartanTestBase {
     /*

    Create a Spartan Flow to run below testCases dynamically

   - POST     /api/spartans
            Create a spartan Map,Spartan class
                name = "API Flow POST"
                gender="Male"
                phone=1231231231l

            - verify status code 201
            - message is "A Spartan is Born!"
            - take spartanID from response and save as a global variable


    - GET  Spartan with spartanID     /api/spartans/{id}


             - verify status code 200
             - verify name is API Flow POST

    - PUT  Spartan with spartanID    /api/spartans/{id}

             Create a spartan Map
                name = "API PUT Flow"
                gender="Female"
                phone=3213213213l

             - verify status code 204

    - GET  Spartan with spartanID     /api/spartans/{id}


             - verify status code 200
             - verify name is API PUT Flow

    - DELETE  Spartan with spartanID   /api/spartans/{id}


             - verify status code 204

     - GET  Spartan with spartanID   /api/spartans/{id}


             - verify status code 404


    Challenges
       Create @Test annotated method for each Request
       Put them in order to execute as expected
                    - Use your googling skills
                    - How to run Junit5 Tests in order  ?

     */

   static int spartanID;

    @Order(1)
    @DisplayName("POST")
    @Test
    public void test1(){

        //   - POST     /api/spartans
        //  Create a spartan Map,Spartan class
        //      name = "API Flow POST"
        //      gender="Male"
        //      phone=1231231231l
        //
        //  - verify status code 201
        //  - message is "A Spartan is Born!"
        //  - take spartanID from response and save as a global variable


        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","API Flow POST");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone",1231231231l);


        JsonPath jsonPath = given().log().ifValidationFails()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestBodyMap)
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        spartanID = jsonPath.getInt("data.id");
        System.out.println("spartanID = " + spartanID);


    }

    @Order(2)
    @DisplayName("GET")
    @Test
    public void test2(){
        //    - GET  Spartan with spartanID     /api/spartans/{id}
        //    - verify status code 200
        //    - verify name is API Flow POST

        Response response = given().log().ifValidationFails()
                .accept(ContentType.JSON)
                .pathParam("id", spartanID)
                .when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("name");
        assertEquals("API Flow POST", name);

    }

    @Order(3)
    @DisplayName("PUT")
    @Test
    public void test3(){

        //    - PUT  Spartan with spartanID    /api/spartans/{id}

// Create a spartan Map
//    name = "API PUT Flow"
//    gender="Female"
//    phone=3213213213l
//
// - verify status code 204

        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","API PUT Flow");
        requestBodyMap.put("gender","Female");
        requestBodyMap.put("phone",3213213213l);


        given()
                .contentType(ContentType.JSON).log().ifValidationFails()
                .pathParam("id", spartanID)
                .body(requestBodyMap)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);


    }


    @Order(4)
    @DisplayName("GET")
    @Test
    public void test4() {
        // - GET  Spartan with spartanID     /api/spartans/{id}
        //   - verify status code 200
        //   - verify name is API PUT Flow

        given()
                .accept(ContentType.JSON).log().ifValidationFails()
                .pathParam("id",spartanID)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and()
                .body("name", equalTo("API PUT Flow"));
    }

    @Order(5)
    @DisplayName("DELETE")
    @Test
    public void test5() {

        //    - DELETE  Spartan with spartanID   /api/spartans/{id}
        //             - verify status code 204

        given().log().ifValidationFails()
                .pathParam("id", spartanID)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
    }


    @Order(6)
    @DisplayName("GET")
    @Test
    public void test6() {
        //     - GET  Spartan with spartanID   /api/spartans/{id}
        //             - verify status code 404
        given().log().ifValidationFails()
                .accept(ContentType.JSON)
                .pathParam("id",spartanID)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);

    }
}
