package com.realworld.wages.serviceIF;

import com.realworld.wages.dto.categoryDto;
import com.realworld.wages.entities.category;

import java.util.List;

public interface IcategoryService {

    /**
     *
     * @param cat
     * @return
     */
    category createCategory(category cat);

    /**
     *
     * @param id
     * @return
     */
    List <category> getCategoryById(Long id);

    /**
     *
     * @return
     */
    List <category> findAllCategory();

    /**
     *
     * @param cat
     * @return
     */
    category updateCategory(Long cat, categoryDto catDto);
}
