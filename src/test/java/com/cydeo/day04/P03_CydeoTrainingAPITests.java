package com.cydeo.day04;

import com.cydeo.utilities.CydeoTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {

      /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
    */

    @DisplayName("GET /student/2")
    @Test
    public void test1(){





    }


}
