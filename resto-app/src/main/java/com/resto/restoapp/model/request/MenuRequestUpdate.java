package com.resto.restoapp.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MenuRequestUpdate {
    private Integer id;
    private String name;
    private Integer price;
    @JsonProperty("isAvailable")
    private boolean isAvailable;
}
