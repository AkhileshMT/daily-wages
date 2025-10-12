package com.realworld.wages.serviceIF;

import com.realworld.wages.dto.PurchaseReceiveDto;

public interface IPurchaseReceiveService {

    /**
     *
     * @param purchaseReceiveDto
     * @param storeId
     * @param poId
     * @return
     */
    public PurchaseReceiveDto createPurchaseReceive(PurchaseReceiveDto purchaseReceiveDto, Long storeId, Long poId);
}
