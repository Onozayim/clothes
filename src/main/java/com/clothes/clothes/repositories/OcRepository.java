package com.clothes.clothes.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Oc;
import com.clothes.clothes.responses.OcWithDetailsResponse;

@Repository
public interface OcRepository extends JpaRepository<Oc, Long>{
    @Query(value = "SELECT new com.clothes.clothes.responses.OcWithDetailsResponse(oc) From Oc oc where oc.user.id = ?1", nativeQuery = false)
    List<OcWithDetailsResponse> getUserOcs(Long user_id);
}
