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

                //.time(lessThan(500L))//this method is storing actual response time
                //.time(greaterThan(100L));



    }
}
