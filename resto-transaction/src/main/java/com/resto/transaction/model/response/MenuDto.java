package com.resto.transaction.model.response;

import lombok.Data;

@Data
public class MenuDto {
    private Integer id;
    private String name;
    private long createdAt;
    private long updatedAt;
    private Integer price;
    private  boolean isAvailable;
}

