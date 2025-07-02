package com.realworld.wages.repository;

import com.realworld.wages.entities.dailyExpensive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface dailyExpensiveRepository extends JpaRepository<dailyExpensive, Long> {

    @Query(value = "SELECT expenses.* From expenses " +
            "Where expenses.user_id=:userId " +
            "AND expenses.expenses_id=:expenseId", nativeQuery = true)
    List<dailyExpensive> ExpenseByUserIdByExpenseId (@Param("userId") Long userId, @Param("expenseId") Long expenseId);

    @Query(value = "SELECT expenses.* From expenses " +
            "Where expenses.user_id=:userId", nativeQuery = true)
    List<dailyExpensive> ExpenseByUserId(@Param("userId") Long userId);
}
