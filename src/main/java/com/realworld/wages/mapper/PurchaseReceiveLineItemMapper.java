package com.realworld.wages.mapper;

import com.realworld.wages.dto.PurchaseReceiveLineItemDto;
import com.realworld.wages.entities.PurchaseReceive;
import com.realworld.wages.entities.PurchaseReceiveLineItem;
import com.realworld.wages.util.stringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReceiveLineItemMapper {

    public static PurchaseReceiveLineItemDto dto(PurchaseReceiveLineItem entity){
        if (entity == null) return null;

        PurchaseReceiveLineItemDto dto = new PurchaseReceiveLineItemDto();
        dto.setReceivedLineItemId(entity.getReceivedLineItemId());
        dto.setPoLineId(entity.getPoLineId());
        dto.setOrdered(entity.getOrdered());
        dto.setReceived(entity.getReceived());
        dto.setRemainingOrder(entity.getRemainingOrder());
        dto.setCostPrice(entity.getCostPrice());
        dto.setLineTotal(entity.getLineTotal());
        dto.setCreatedDate(stringUtil.getIndiaTime(entity.getCreatedDate()));
        dto.setModifiedDate(stringUtil.getIndiaTime(entity.getModifiedDate()));
        return dto;
    }

    public static PurchaseReceiveLineItem entity(PurchaseReceiveLineItemDto dto, PurchaseReceive receive){
        if(dto == null) return null;

        PurchaseReceiveLineItem entity = new PurchaseReceiveLineItem();
        entity.setReceivedLineItemId(dto.getReceivedLineItemId());
        entity.setPoLineId(dto.getPoLineId());
        entity.setOrdered(dto.getOrdered());
        entity.setReceived(dto.getReceived());
        entity.setRemainingOrder(dto.getRemainingOrder());
        entity.setCostPrice(dto.getCostPrice());
        entity.setLineTotal(dto.getLineTotal());
        entity.setPurchaseReceive(receive);
        return entity;
    }

    public static List<PurchaseReceiveLineItemDto> toDtoList(List<PurchaseReceiveLineItem> entities) {
        List<PurchaseReceiveLineItemDto> list = new ArrayList<>();
        if (entities != null) {
            for (PurchaseReceiveLineItem e : entities) list.add(dto(e));
        }
        return list;
    }

    public static List<PurchaseReceiveLineItem> toEntityList(List<PurchaseReceiveLineItemDto> dtos, PurchaseReceive poReceive) {
        List<PurchaseReceiveLineItem> list = new ArrayList<>();
        if (dtos != null) {
            for (PurchaseReceiveLineItemDto dto : dtos) list.add(entity(dto, poReceive));
        }
        return list;
    }
}
