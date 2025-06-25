package com.principal.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.principal.demo.model.Carrito;

import jakarta.transaction.Transactional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    @Modifying
    @Transactional
    @Query(value ="UPDATE carrito SET cantidad = :cantidad WHERE id_producto = :idProducto AND id_usuario = :idUsuario",nativeQuery = true)
    boolean updateCantidadCarrito(int idUsuario,int idProducto,int cantidad);

    @Modifying
    @Transactional
    @Query(value ="DELETE carrito WHERE id_producto = :idProducto AND id_usuario = :idUsuario",nativeQuery = true)
    boolean deleteProductoCarrito(int idUsuario,int idProducto);

    List<Carrito> findByUsuarioId(Integer idUsuario);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carrito (id_usuario, id_producto, cantidad, total) VALUES (:idUsuario, :idProducto, :cantidad, :total)", nativeQuery = true)
    void saveCarritoByIdUsuario(@Param("idUsuario") int idUsuario,
            @Param("idProducto") int idProducto,
            @Param("cantidad") int cantidad,
            @Param("total") double total);
}
