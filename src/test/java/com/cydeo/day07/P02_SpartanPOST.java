package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_SpartanPOST extends SpartanTestBase {
    /**
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is 'A Spartan is Born!'
     "name": "John Doe",
     "gender": "Male",
     "phone": 8877445596
     */

    @DisplayName("POST Spartan with String body")
    @Test
    public void test1(){





    }


    @DisplayName("POST Spartan with Map body")
    @Test
    public void test2(){




    }

    @DisplayName("POST Spartan with Spartan POJO")
    @Test
    public void test3(){




    }

    @DisplayName("POST Spartan with Spartan POJO and GET same Spartan")
    @Test
    public void test4(){



    }



}
