package com.realworld.wages.mapper;

import com.realworld.wages.dto.categoryDto;
import com.realworld.wages.entities.category;
import com.realworld.wages.util.stringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class categoryMapper {


    public categoryDto mapToDto(category cat){
       categoryDto catDto = new categoryDto();
       catDto.setCategoryId(cat.getCategoryId());
       catDto.setCategoryName(cat.getCategoryName());
       catDto.setActive(cat.isActive());
       catDto.setCreatedDate(stringUtil.getIndiaTime(cat.getCreatedDate()));
       catDto.setModifiedDate(stringUtil.getIndiaTime(cat.getModifiedDate()));
       return catDto;
    }

    public category mapToEntity(categoryDto catDto){
       category cat = new category();
       cat.setCategoryName(catDto.getCategoryName());
       cat.setActive(catDto.isActive());
       cat.setCreatedDate(stringUtil.getIndiaTime(catDto.getCreatedDate()));
       cat.setModifiedDate(stringUtil.getIndiaTime(catDto.getCreatedDate()));
       return cat;
    }
}
