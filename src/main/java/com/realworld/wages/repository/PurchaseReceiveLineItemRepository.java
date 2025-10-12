package com.realworld.wages.repository;

import com.realworld.wages.entities.PurchaseReceiveLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseReceiveLineItemRepository extends JpaRepository<PurchaseReceiveLineItem, Long> {

    @Query(value = "SELECT COALESCE(SUM(received), 0) FROM purchase_receive_line_item WHERE po_line_id = :poLineId",
            nativeQuery = true)
    Long sumReceivedByPoLineId(@Param("poLineId") Long poLineId);
}
