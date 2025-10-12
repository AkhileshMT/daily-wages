package com.realworld.wages.repository;

import com.realworld.wages.entities.PurchaseReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseReceiveRepository extends JpaRepository<PurchaseReceive, Long> {

    Optional<PurchaseReceive> findFirstByPoIdAndStatus(Long poId, String status);

}
