package com.realworld.wages.mapper;

import com.realworld.wages.dto.PurchaseReceiveDto;
import com.realworld.wages.entities.PurchaseReceive;
import com.realworld.wages.entities.PurchaseReceiveLineItem;

import java.util.List;

public class PurchaseReceiveMapper {

    public static PurchaseReceiveDto dto(PurchaseReceive entity){
        if (entity == null) return null;

        PurchaseReceiveDto dto = new PurchaseReceiveDto();
        dto.setReceivedId(entity.getReceivedId());
        dto.setPoId(entity.getPoId());
        dto.setSupplierId(entity.getSupplierId());
        dto.setStatus(entity.getStatus());
        dto.setTotalQuantity(entity.getTotalQuantity());
        dto.setUpdatedTotalAmount(entity.getUpdatedTotalAmount());

        if (entity.getLineItems() != null) {
            dto.setLineItems(PurchaseReceiveLineItemMapper.toDtoList(entity.getLineItems()));
        }

        return dto;
    }

    public static PurchaseReceive entity(PurchaseReceiveDto dto){
        if (dto == null) return null;

        PurchaseReceive entity = new PurchaseReceive();
        entity.setReceivedId(dto.getReceivedId());
        entity.setPoId(dto.getPoId());
        entity.setSupplierId(dto.getSupplierId());
        entity.setStatus(dto.getStatus());
        entity.setTotalQuantity(dto.getTotalQuantity());
        entity.setUpdatedTotalAmount(dto.getUpdatedTotalAmount());

        if (dto.getLineItems() != null) {
            List<PurchaseReceiveLineItem> lineEntities = PurchaseReceiveLineItemMapper.toEntityList(dto.getLineItems(), entity);
            entity.setLineItems(lineEntities);
        }

        return entity;
    }
}
