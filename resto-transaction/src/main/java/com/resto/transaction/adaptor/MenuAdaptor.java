package com.resto.transaction.adaptor;

import com.resto.transaction.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "resto-app", contextId = "menuContext")
public interface MenuAdaptor {

    @GetMapping("menu/v1/detail/{id}")
    ResponseEntity<Response> getMenuById(@PathVariable Integer id);
}
