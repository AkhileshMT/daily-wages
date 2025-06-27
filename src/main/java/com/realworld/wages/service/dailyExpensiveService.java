package com.realworld.wages.service;

import com.realworld.wages.entities.category;
import com.realworld.wages.entities.dailyExpensive;
import com.realworld.wages.entities.storeEarning;
import com.realworld.wages.entities.users;
import com.realworld.wages.repository.categoryRepository;
import com.realworld.wages.repository.dailyExpensiveRepository;
import com.realworld.wages.repository.storeEarningRepository;
import com.realworld.wages.repository.userRepo;
import com.realworld.wages.serviceIF.IdailyExpensiveService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class dailyExpensiveService implements IdailyExpensiveService {

    @Autowired
    private userRepo repo;

    @Autowired
    private dailyExpensiveRepository expenseRepo;

    @Autowired
    private storeEarningRepository storeEarn;

    @Autowired
    private categoryRepository catRepo;

    @Override
    public dailyExpensive createExpensive(long userId, dailyExpensive expensive) {
        users user = repo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        category cat = catRepo.findById(expensive.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + expensive.getCategoryId()));
        expensive.setUserId(userId);
        expensive.setCategoryName(cat.getCategoryName());

        dailyExpensive expenses = expenseRepo.save(expensive);
        storeEarning earning = storeEarn.findById(userId).orElseThrow(() ->  new EntityNotFoundException("Store earning not found for user: " + userId));


        Long currentAmount = earning.getCurrentAmount();
        Long expenseAmount = expensive.getAmount();

        if (currentAmount < expenseAmount) {
            throw new IllegalArgumentException("Insufficient funds. Cannot deduct expense.");
        }

        earning.setCurrentAmount(currentAmount - expenseAmount);

        storeEarn.save(earning);

        return expenses;
    }
}
