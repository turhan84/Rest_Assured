package com.cydeo.pojo.homework03;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Place{

    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("place name")
    private String placeName;

}
