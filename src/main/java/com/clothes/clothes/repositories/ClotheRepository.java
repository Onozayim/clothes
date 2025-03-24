package com.clothes.clothes.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Clothe;
import com.clothes.clothes.responses.CltoheResponse;

@Repository
public interface ClotheRepository extends JpaRepository<Clothe, Long>{
    @Query("SELECT new com.clothes.clothes.responses.CltoheResponse(c) From Clothe c")
    Page<CltoheResponse> findAllClothesWithoutStock(Pageable pageable);

}