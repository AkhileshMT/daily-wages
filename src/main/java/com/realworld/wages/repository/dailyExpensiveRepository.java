package com.realworld.wages.repository;

import com.realworld.wages.entities.dailyExpensive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dailyExpensiveRepository extends JpaRepository<dailyExpensive, Long> {
}
