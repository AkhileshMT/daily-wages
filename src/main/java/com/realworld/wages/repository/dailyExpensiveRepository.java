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


    @Query(value = "SELECT * FROM expenses " +
            "WHERE user_id = :userId " +
            "AND (LOWER(title) LIKE LOWER(CONCAT('%', :val, '%')) " +
            "OR LOWER(description) LIKE LOWER(CONCAT('%', :val, '%')) " +
            "OR CAST(amount AS CHAR) LIKE CONCAT('%', :val, '%') " +
            "OR CAST(expenses_id AS CHAR) LIKE CONCAT('%', :val, '%'))", nativeQuery = true)
    List<dailyExpensive> ExpensiveSearch(@Param("userId") Long userId, @Param("val") String val);


    @Query(value = "SELECT * FROM expenses " +
            "WHERE user_id = :userId AND expenses_id = :expenseId",
            nativeQuery = true)
    List<dailyExpensive> searchByExpenseId(@Param("userId") Long userId , @Param("expenseId") Long expenseId);
}
