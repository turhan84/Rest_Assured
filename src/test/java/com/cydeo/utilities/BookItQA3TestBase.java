package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BookItQA3TestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://api.qa3.bookit.cydeo.com";
    }
}
