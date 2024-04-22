package com.cydeo.day06;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P02_SpartanDeserializationPOJO extends SpartanTestBase {

    @DisplayName("GET Single spartan for deserialization to POJO (Spartan class)")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        /*
        {
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
}
         */

        System.out.println("----------RESPONSE----------");
        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());

        //JSONPATH
        System.out.println("--------- JSONPATH ---------");
        JsonPath jsonPath = response.jsonPath();

        Spartan spartanJP = jsonPath.getObject("", Spartan.class);
        System.out.println("spartanJP.getId() = " + spartanJP.getId());
        System.out.println("spartanJP.getName() = " + spartanJP.getName());
        System.out.println("spartanJP.getGender() = " + spartanJP.getGender());
        System.out.println("spartanJP.getPhone() = " + spartanJP.getPhone());
    }

    @DisplayName("GET Spartans from search endpoint and deserialize to POJO")
    @Test
    public void test2(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();
        //I want to get 10th spartan from content array and save into the Spartan object
        //response.as("content[9]",Spartan.class)
        //we cannot do with as() method since it does not support path and class type at the same time

        JsonPath jsonPath = response.jsonPath();

        Spartan spartan = jsonPath.getObject("content[9]", Spartan.class);
        System.out.println("spartan = " + spartan);



    }

    @DisplayName("GET Spartans from search endpoint for deserialization to Search class")
    @Test
    public void test3(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        Search search = response.as(Search.class);

        System.out.println("search.getTotalElement() = " + search.getTotalElement());
        System.out.println("search.getContent() = " + search.getContent());

        //print second spartan from  content
        System.out.println("search.getContent().get(1) = " + search.getContent().get(1));

        //get me second spartan name
        System.out.println("search.getContent().get(1).getName() = " + search.getContent().get(1).getName());


    }

    @DisplayName("GET Spartans from search endpoint for deserialization to List of Spartan")
    @Test
    public void test4(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Spartan> allSpartans = jsonPath.getList("content", Spartan.class);

        for (Spartan spartan : allSpartans) {
            System.out.println("spartan = " + spartan);
        }

        System.out.println("allSpartans.get(1).getId() = " + allSpartans.get(1).getId());


    }


}
