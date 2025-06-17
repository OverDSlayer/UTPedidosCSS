package com.principal.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.demo.model.Producto;
import com.principal.demo.repository.ProductoRepository;
@Service
public class ProductoServiceImpl implements ProductosService{
    
    private final ProductoRepository productosRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productosRepository = productoRepository;
    }
    
    @Override
    public List<Producto> findAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Optional<Producto> findProductoById(Integer id) {
        return productosRepository.findProductoById(id);
    }

    @Override
    public Optional<Producto> findProductoPorNombre(String nombre) {
        return Optional.empty();
    }

    @Override
    public Producto saveUser(Producto producto) {
        if (producto.getImagenUrl().isEmpty()) {
            producto.setImagenUrl("/imagenes/imagenpordefecto.png");
        }
        return productosRepository.save(producto);
    }

    @Override
    public void deleteUserById(Long id) {

    }

    public List<Producto> obtenerProductosPorCategoria(String categoria){
        return productosRepository.findAllByCategoriaNombre(categoria);
    }

        public List<Producto> findAllByCategoriaNombre(String nombre){
        return productosRepository.findAllByCategoriaNombre(nombre);
    }
}