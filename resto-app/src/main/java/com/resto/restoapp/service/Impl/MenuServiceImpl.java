package com.resto.restoapp.service.Impl;

import com.resto.restoapp.model.request.MenuRequest;
import com.resto.restoapp.model.request.MenuRequestUpdate;
import com.resto.restoapp.model.response.MenuDto;
import com.resto.restoapp.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${base.url}")
    private String baseUrl;

    @Override
    public List<MenuDto> findAll() {
            List<MenuDto> response = restTemplate.getForObject(baseUrl + "/menu", List.class);
            log.info("Response Find All = {}", response);
            return response;
    }

    @Override
    public MenuDto findById(Integer id) {
        MenuDto response = restTemplate.getForObject(baseUrl + "/menu/{id}", MenuDto.class, id);
        log.info("Response Get By Id = {}", response);
        return response;
    }

    @Override
    public boolean insert(MenuRequest request) {
        HttpEntity<MenuRequest> entity = new HttpEntity<>(request);
        log.info("menu request = {}", request);
        ResponseEntity<MenuDto> response = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, MenuDto.class);
        if (response.getStatusCode() == HttpStatus.OK){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(MenuRequestUpdate request) {
        MenuDto thisMenuResponse = findById(request.getId());

        if (StringUtils.isNotEmpty(request.getName())) {
            thisMenuResponse.setName(request.getName());
        }

        if (request.getPrice() != null) {
            thisMenuResponse.setPrice(request.getPrice());
        }

        if (request.isAvailable() !=thisMenuResponse.isAvailable()){
            thisMenuResponse.setAvailable(request.isAvailable());
        }

        thisMenuResponse.setUpdatedAt(System.currentTimeMillis());
        HttpEntity<MenuDto> requestUpdate = new HttpEntity<>(thisMenuResponse);
        ResponseEntity<MenuDto> response = restTemplate.exchange(baseUrl + "/menu/" + request.getId(), HttpMethod.PUT, requestUpdate, MenuDto.class);
        MenuDto responseData = response.getBody();
        log.info("Response Update = {}", responseData);
        if (response.getStatusCode() == HttpStatus.OK){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
       try {
           MenuDto menu = findById(id);

           restTemplate.delete(baseUrl + "/menu/" + id, menu);

           log.info("Response Delete = {}", menu);
           return true;
       }catch (Exception e){
           return false;
       }
    }
}
