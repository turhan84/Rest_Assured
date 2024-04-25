package com.cydeo.pojo.homework03;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryPOJO {

    private int id;
    private String name;
    private String image;

}
