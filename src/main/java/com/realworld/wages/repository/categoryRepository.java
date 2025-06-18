package com.realworld.wages.repository;

import com.realworld.wages.entities.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<category, Long> {

}
