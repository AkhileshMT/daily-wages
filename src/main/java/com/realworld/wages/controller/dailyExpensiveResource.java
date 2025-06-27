package com.realworld.wages.controller;

import com.realworld.wages.dto.dailyExpensiveDto;
import com.realworld.wages.entities.dailyExpensive;
import com.realworld.wages.mapper.dailyExpensiveMapper;
import com.realworld.wages.service.dailyExpensiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailyExpense")
public class dailyExpensiveResource {
    /**
     * Author Akhilesh
     */
    @Autowired
    private dailyExpensiveMapper expenseMapper;

    @Autowired
    private dailyExpensiveService expenseService;

    @PostMapping(value = "/create/{userId}",  produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public dailyExpensiveDto create (@PathVariable Long userId, @Validated @RequestBody dailyExpensiveDto expensiveDto){
        dailyExpensive expensive = expenseMapper.mapToEntity(expensiveDto);
        return expenseMapper.mapToDto(expenseService.createExpensive(userId,expensive));
    }
}
