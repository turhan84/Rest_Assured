package com.cydeo.day13;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_SpartanMock extends SpartanTestBase {


//    @BeforeAll
//    public static void init(){
//        baseURI= "https://d1a6cbb7-8f62-4542-b046-cfe95b366a08.mock.pstmn.io";
//    }

    @DisplayName("GET /api/hello")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.TEXT)
                .log().all()
                .when().get("/api/hello")
                .then().statusCode(200)
                .extract().response();

        assertEquals("Hello from Sparta",response.asString());
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        response.prettyPrint();


    }

}
