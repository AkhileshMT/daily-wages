package com.realworld.wages.repository;

import com.realworld.wages.entities.storeEarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface storeEarningRepository extends JpaRepository<storeEarning,Long> {

    @Query(value = "SELECT store_earning.* FROM store_earning", nativeQuery = true)
    List <storeEarning> findAllEarning();


    @Query(value = "SELECT store_earning.* FROM store_earning "
    + "WHERE store_earning.user_id=:userId " +
            "AND store_earning.store_earning_id=:storeEarningId", nativeQuery = true
    )
    List <storeEarning> findByIdEarningId(@Param("userId") Long userId, @Param("storeEarningId") Long storeEarningId);
}
