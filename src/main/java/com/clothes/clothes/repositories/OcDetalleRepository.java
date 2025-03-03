package com.clothes.clothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.OcDetalle;

@Repository
public interface OcDetalleRepository extends JpaRepository <OcDetalle, Long> {
    
}
