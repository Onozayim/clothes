package com.clothes.clothes.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Oc;

@Repository
public interface OcRepository extends JpaRepository<Oc, Long>{
}
