package com.resto.restoapp.controller;

import com.resto.restoapp.model.request.MenuRequest;
import com.resto.restoapp.model.request.MenuRequestUpdate;
import com.resto.restoapp.model.response.MenuDto;
import com.resto.restoapp.service.MenuService;
import com.resto.restoapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @PostMapping("v1/create")
    ResponseEntity<Response> saveMenu(@RequestBody MenuRequest request) throws HttpClientErrorException {
        boolean isSuccess = menuService.insert(request);
        Response response;

        if (isSuccess){
            response = new Response(null, "Success insert", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "Failed to insert", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("v1/update")
    ResponseEntity<Response> updateMenu(@RequestBody MenuRequestUpdate request)throws HttpClientErrorException {
        boolean isSuccess = menuService.update(request);
        Response response;
        if (isSuccess){
            response = new Response(null, "Success update", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response =  new Response(null, "Failed to update", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("v1/find-all")
    ResponseEntity<Response> getMenu() throws HttpClientErrorException {
        List<MenuDto> listData = menuService.findAll();
        Response response;
        if (listData != null || !listData.isEmpty()){
            response = new Response(listData, "Here your data", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "No data found", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("v1/detail/{id}")
    ResponseEntity<Response> getMenuById(@PathVariable Integer id)  throws HttpClientErrorException{
        MenuDto data = menuService.findById(id);
        Response response;
        if (!ObjectUtils.isEmpty(data)){
            response = new Response(data, "Here your data", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "No data found", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("v1/delete/{id}")
    ResponseEntity<Response> deleteMenu(@PathVariable Integer id) throws HttpClientErrorException{
        boolean isSuccess = menuService.delete(id);
        Response response;
        if (isSuccess){
            response = new Response(null, "Success to delete", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "Failed to delete", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
