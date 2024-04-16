package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.80.79.105:1000/ords/hr";
        //MyIpAddress:1000/ords/hr
    }

}
