package com.resto.restoapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MenuRequest {
    private String name;
    private Integer price;
    @JsonProperty("isAvailable")
    private  boolean isAvailable;
}
