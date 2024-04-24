package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class ZipCodeTestBase {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://api.zippopotam.us";
    }

    @AfterAll
    public static void destroy(){

        RestAssured.reset();
    }

}
