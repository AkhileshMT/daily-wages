package com.realworld.wages.service;

import com.realworld.wages.dto.categoryDto;
import com.realworld.wages.entities.category;
import com.realworld.wages.entities.users;
import com.realworld.wages.repository.categoryRepository;
import com.realworld.wages.repository.userRepo;
import com.realworld.wages.serviceIF.IcategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class categoryService implements IcategoryService {

    @Autowired
    categoryRepository catRepo;

    @Autowired
    private userRepo repo;

    @Override
    public category createCategory(Long userId,category cat) {
        users user = repo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        cat.setUserId(userId);
        return catRepo.save(cat);
    }

    @Override
    public List<category> getCategoryById(Long id) {
        category cat = catRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        return List.of(cat);
    }

    @Override
    public List<category> findAllCategory() {
        return (catRepo.findAll());
    }

    @Override
    public category updateCategory(Long catId, categoryDto catDto) {
        category cat = catRepo.findById(catId).orElseThrow();
        cat.setCategoryName(catDto.getCategoryName());
        cat.setActive(catDto.isActive());
        return catRepo.save(cat);
    }
}
