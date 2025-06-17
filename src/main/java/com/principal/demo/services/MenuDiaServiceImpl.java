package com.principal.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.demo.model.MenuDia;
import com.principal.demo.repository.MenuDiaRepository;

@Service
public class MenuDiaServiceImpl implements MenuDiaService {

    private final MenuDiaRepository menuDia;
    @Autowired
    public MenuDiaServiceImpl(MenuDiaRepository menuDia) {
        this.menuDia = menuDia;
    }

    @Override
    public List<MenuDia> findAllMenuDias() {
        return menuDia.findAll();
    }

    @Override
    public MenuDia saveMenudia(MenuDia menu) {
        return menuDia.save(menu);
    }
}