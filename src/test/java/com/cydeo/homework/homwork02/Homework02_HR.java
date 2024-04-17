package com.cydeo.homework.homwork02;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                .pathParam("country_id","US")
                .when()
                .get("/countries/{country_id}");

        //response.prettyPrint();

        // System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //- Then status code is 200
        assertEquals(200,response.statusCode());

        //- And Content - Type is Json
        assertTrue(response.contentType().contains("json"));

        //- And country_id is US
        assertEquals("US",response.path("country_id"));

        //- And Country_name is United States of America
        assertEquals("United States of America",response.path("country_name"));

        //And Region_id is 2
        int regionID = response.path("region_id");
        assertEquals(2,regionID);



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

    @DisplayName("GET Request to /employees")
    @Test
    public void test2() {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q","{\"department_id\":80}")
                .and()
                .queryParam("job_ids","'SA'")
                .when()
                .get("/employees");

        //response.prettyPrint();

        //- Then status code is 200
        assertEquals(200,response.statusCode());
        //- And Content - Type is Json
        System.out.println("response.contentType() = " + response.contentType());

        //- And all job_ids start with 'SA'
        List<String> allJobIDs = response.path("items.job_id");

        for (String id : allJobIDs) {
            assertTrue(id.contains("SA"));
            //    System.out.println("id = " + id);
        }
        //- And all department_ids are 80
        List<Integer> allDepartmentIDs = response.path("items.department_id");

        for (Integer id : allDepartmentIDs) {
            assertEquals(80,id);
            //  System.out.println("id = " + id);
        }

        //- Count is 25

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        int count = response.path("limit");
        assertEquals(25,count);


    }
    /*
    TASK 3 :
    - Given accept type is Json
    - Query param value q={"region_id":3}
    - When users sends request to /countries
    - Then status code is 200
    - And all regions_id is 3
    - And count is 6
    - And hasMore is false
    - And Country_name are;
    Australia,China,India,Japan,Malaysia,Singapore
    */
    @DisplayName("GET Request to /countries")
    @Test
    public void test3() {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":3}")
                .and()
                .queryParam("job_ids", "'SA'")
                .when()
                .get("/countries");

        //- Then status code is 200
        assertEquals(200,response.statusCode());
        //- And all regions_id is 3
        List<Integer> allRegionIDs = response.path("items.region_id");


        for (Integer id : allRegionIDs) {
            assertEquals(3,id);

        }

        //- And count is 6

        int count = response.path("count");

        assertEquals(6,count);

        //- And hasMore is false
        assertEquals(false,response.path("hasMore") );

        //- And Country_name are;
        //Australia,China,India,Japan,Malaysia,Singapore
        ArrayList<String> expectedCountyNames = new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));

        List<String> allCountryNames = response.path("items.country_name");
        System.out.println("allCountryNames = " + allCountryNames);

        assertEquals(expectedCountyNames,allCountryNames);

    }
}
