package com.realworld.wages.mapper;

import com.realworld.wages.dto.dailyExpensiveDto;
import com.realworld.wages.entities.dailyExpensive;
import com.realworld.wages.util.stringUtil;
import org.springframework.stereotype.Component;

@Component
public class dailyExpensiveMapper {

    public dailyExpensiveDto mapToDto(dailyExpensive expense){
        dailyExpensiveDto expenseDto = new dailyExpensiveDto();

        expenseDto.setExpensesId(expense.getExpensesId());
        expenseDto.setTitle(expense.getTitle());
        expenseDto.setDescription(expense.getDescription());
        expenseDto.setCategoryName(expense.getCategoryName());
        expenseDto.setAmount(expense.getAmount());
        expenseDto.setName(expense.getName());
        expenseDto.setFilePath(expense.getFilePath());
        expenseDto.setCategoryId(expense.getCategoryId());
        expenseDto.setCreatedDate(stringUtil.getIndiaTime(expense.getCreatedDate()));
        expenseDto.setModifiedDate(stringUtil.getIndiaTime(expense.getModifiedDate()));

        return expenseDto;
    }


    public dailyExpensive mapToEntity(dailyExpensiveDto expensiveDto){
        dailyExpensive expensive = new dailyExpensive();

        expensive.setExpensesId(expensiveDto.getExpensesId());
        expensive.setTitle(expensiveDto.getTitle());
        expensive.setDescription(expensiveDto.getDescription());
        expensive.setCategoryName(expensiveDto.getCategoryName());
        expensive.setAmount(expensiveDto.getAmount());
        expensive.setName(expensiveDto.getName());
        expensive.setFilePath(expensiveDto.getFilePath());
        expensive.setCategoryId(expensiveDto.getCategoryId());
        expensive.setCreatedDate(stringUtil.getIndiaTime(expensiveDto.getCreatedDate()));
        expensive.setModifiedDate(stringUtil.getIndiaTime(expensiveDto.getModifiedDate()));

        return expensive;
    }
}
