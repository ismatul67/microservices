package com.resto.restoapp.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {

    private Object data;
    private String message;
    private Boolean result;

    public Response(Object data, String message, Boolean result){
        this.data = data;
        this.message = message;
        this.result = result;
    }

}