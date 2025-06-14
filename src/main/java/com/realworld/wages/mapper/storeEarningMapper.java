package com.realworld.wages.mapper;

import com.realworld.wages.dto.storeEarningDto;
import com.realworld.wages.entities.storeEarning;
import com.realworld.wages.util.stringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class storeEarningMapper {

    private ModelMapper modelMapper;

    public storeEarningDto mapToDto(storeEarning earning){
        storeEarningDto earningDto = new storeEarningDto();
//        storeEarningDto earningDto = modelMapper.map(earning, storeEarningDto.class);
        earningDto.setUserId(earning.getUserId());
        earningDto.setStoreEarningId(earning.getStoreEarningId());
        earningDto.setAmountAdded(earning.getAmountAdded());
        earningDto.setDescription(earning.getDescription());
        earningDto.setCurrentAmount(earning.getCurrentAmount());
        earningDto.setCreatedDate(stringUtil.getIndiaTime(earning.getCreatedDate()));
        earningDto.setModifiedDate(stringUtil.getIndiaTime(earning.getModifiedDate()));
        return earningDto;
    }

    public storeEarning mapToEntity(storeEarningDto earningDto){
//        return modelMapper.map(earningDto, storeEarning.class);
        storeEarning earning = new storeEarning();
        earning.setAmountAdded(earningDto.getAmountAdded());
        earning.setDescription(earningDto.getDescription());
        earning.setCurrentAmount(earningDto.getCurrentAmount());
        earning.setCreatedDate(stringUtil.getIndiaTime(earningDto.getCreatedDate()));
        earning.setModifiedDate(stringUtil.getIndiaTime(earningDto.getModifiedDate()));

        return earning;
    }
}
