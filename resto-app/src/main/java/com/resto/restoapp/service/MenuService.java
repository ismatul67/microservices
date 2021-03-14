package com.resto.restoapp.service;

import com.resto.restoapp.model.request.MenuRequest;
import com.resto.restoapp.model.request.MenuRequestUpdate;
import com.resto.restoapp.model.response.MenuDto;

import java.util.List;

public interface MenuService {

    List<MenuDto> findAll();
    MenuDto findById(Integer id);
    boolean insert(MenuRequest request);
    boolean update(MenuRequestUpdate request);
    boolean delete(Integer id);
}
