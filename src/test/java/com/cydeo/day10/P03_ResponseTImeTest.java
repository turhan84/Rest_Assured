package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class P03_ResponseTImeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){

        Response response = given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //.time(lessThan(500L))//this method is storing actual response time
                //.time(greaterThan(100L));
                .time(both(greaterThan(500L)).and(lessThan(900L)))
                .extract().response();

        System.out.println("response.time() = " + response.getTime());
        System.out.println("response.getTimeIn(TimeUnit.MICROSECONDS) = " + response.getTimeIn(TimeUnit.NANOSECONDS));


    }
}
