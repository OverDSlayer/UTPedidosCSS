package com.principal.demo.controller;

import java.util.List;

import com.principal.demo.model.Carrito;
import com.principal.demo.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.principal.demo.model.Producto;
import com.principal.demo.services.CarritoServiceImpl;
import com.principal.demo.services.ProductoServiceImpl;

@Controller
public class ProductoController {
    @Autowired
    ProductoServiceImpl productosServiceImpl;
    @Autowired
    CarritoServiceImpl carritoServiceImpl;
    private CarritoService carritoService;

    @GetMapping("/catalogo")
    public String index(@RequestParam(required = false) String categoria,Model model) {
        List<Producto> productos = productosServiceImpl.findAllProductos();
        if(categoria != null && !categoria.isEmpty()){
            productos = productosServiceImpl.obtenerProductosPorCategoria(categoria);
        }
        model.addAttribute("productos",productos);
        return "catalogo";
    }

    @PostMapping("/catalogo/subir")
    public String subirAlCarrito(
        @RequestParam Integer idProducto,
        @RequestParam Integer idUsuario,
        @RequestParam int cantidad,
        RedirectAttributes redirectAttributes
    ){
        int nuevaCantidad = 0;
        double nuevoTotal = 0;
        Producto producto = productosServiceImpl.findProductoById(idProducto).orElse(null);
        double total = cantidad * producto.getPrecio();

        //obtengo la cantidad de cada producto por el id del usuario
        List<Carrito> carrito = carritoServiceImpl.obtenerCarritosPorUsuario(idUsuario);

        for(Carrito carro : carrito){
            if(carro.getProducto().getId() == idProducto){
                nuevaCantidad = carro.getCantidad() + cantidad;
                nuevoTotal = nuevaCantidad * carro.getProducto().getPrecio();
                if(carritoServiceImpl.actualizarProductoAgregado(idUsuario,idProducto,nuevaCantidad,nuevoTotal) > 0){
                    redirectAttributes.addFlashAttribute("message","Se añadio al carrito correctamente");
                    return "redirect:/catalogo";
                }
            }
        }
        carritoServiceImpl.saveCarrito(idUsuario,idProducto,cantidad,total);

        redirectAttributes.addFlashAttribute("message","Se añadio al carrito correctamente");
        return "redirect:/catalogo";
    }
}