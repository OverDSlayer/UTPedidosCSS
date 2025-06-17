package com.principal.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.demo.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer>{
    List<Producto> findAllByCategoriaNombre(String nombre);
    Optional<Producto> findProductoById(Integer id);
    List<Producto> findAllById(Integer id);
}
