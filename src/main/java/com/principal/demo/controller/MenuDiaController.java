package com.principal.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.principal.demo.model.Producto;
import com.principal.demo.services.MenuDiaServiceImpl;
import com.principal.demo.services.ProductoServiceImpl;

@Controller
public class MenuDiaController {
    @Autowired
    private ProductoServiceImpl productosServiceImpl;
    @Autowired
    private MenuDiaServiceImpl menuDiaServiceImpl;

    @GetMapping("/menuDia")
    public String menuDia(Model model) {
        List<Producto> productosMenuEconomico = productosServiceImpl.findAllByCategoriaNombre("MENU ECONOMICO");
        model.addAttribute("menusEconomicos",productosMenuEconomico);
        return "menuDia";
    }
}