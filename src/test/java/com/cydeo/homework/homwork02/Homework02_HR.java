package com.cydeo.homework.homwork02;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.Query;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework02_HR extends HrTestBase {

/*
  TASK 1 :
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
And Region_id is 2
*/
@DisplayName("GET Request to /countries")
@Test
public void test1() {

    Response response = given()
            .accept(ContentType.JSON)
            .when()
            .get("/countries");

    //response.prettyPrint();

    System.out.println("response.path(\"limit\") = " + response.path("limit"));

    //- Then status code is 200
  assertEquals(200,response.statusCode());

    //- And Content - Type is Json
    System.out.println("response.contentType() = " + response.contentType());

    //- And country_id is US

    //- And Country_name is United States of America

    //And Region_id is 2


}

/*
TASK 2 :
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
*/

/*
TASK 3 :
- Given accept type is Json
- Query param value q={“region_id":3}
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
*/

}
