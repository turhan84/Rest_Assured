package com.cydeo.pojo.homework03;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private int id;
    private String title;
    private int price;

    @JsonProperty("images")
    private List<String> allImages;

    private CategoryPOJO category;

}
