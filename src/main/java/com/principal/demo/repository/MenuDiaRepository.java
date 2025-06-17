package com.principal.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.demo.model.MenuDia;


@Repository
public interface MenuDiaRepository extends JpaRepository<MenuDia, Integer> {
}