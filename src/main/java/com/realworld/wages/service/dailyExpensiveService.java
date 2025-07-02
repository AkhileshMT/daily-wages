package com.realworld.wages.service;

import com.realworld.wages.dto.dailyExpensiveDto;
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

import java.util.List;

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

        System.out.println(storeEarn.findByUserIdNew(userId) +"Repo UserId");
        storeEarning earning = storeEarn.findByUserIdNew(userId);
        System.out.println(earning+" UserId");
        Long currentAmount = earning.getCurrentAmount();
        Long expenseAmount = expensive.getAmount();

        if (currentAmount < expenseAmount) {
            throw new IllegalArgumentException("Insufficient funds. Cannot deduct expense.");
        }

        earning.setCurrentAmount(currentAmount - expenseAmount);

        dailyExpensive expenses = expenseRepo.save(expensive);
        storeEarn.save(earning);

        return expenses;
    }

    public List<dailyExpensive> getExpensiveById(Long userId, Long expenseId){
        return expenseRepo.ExpenseByUserIdByExpenseId(userId,expenseId);
    }

    public List<dailyExpensive> getExpensiveByUserId(Long userId){
        return expenseRepo.ExpenseByUserId(userId);
    }

    public dailyExpensive put(Long expenseId, Long userId, dailyExpensiveDto expenseDto){
        dailyExpensive expenses = expenseRepo.findById(expenseId).orElseThrow(() -> new EntityNotFoundException("Expense Id not found with id: " + expenseId));

        Long oldAmount = expenses.getAmount();
        Long newAmount = expenseDto.getAmount();

        expenses.setAmount(expenseDto.getAmount());
        expenses.setTitle(expenseDto.getTitle());
        expenses.setDescription(expenseDto.getDescription());
        expenses.setCategoryId(expenseDto.getCategoryId());

        //Most Important Part Of the Game!!!!!!!!!

        storeEarning earning = storeEarn.findByUserIdNew(userId);

        Long currentAmount = earning.getCurrentAmount();

        Long updatedEarning = currentAmount + oldAmount - newAmount;

        earning.setCurrentAmount(updatedEarning);

        expenseRepo.save(expenses);
        storeEarn.save(earning);

        return expenses;

    }

}
