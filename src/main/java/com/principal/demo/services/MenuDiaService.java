package com.principal.demo.services;

import java.util.List;

import com.principal.demo.model.MenuDia;

public interface MenuDiaService {
    List<MenuDia> findAllMenuDias();
    MenuDia saveMenudia(MenuDia menu);
}