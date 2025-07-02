package com.realworld.wages.controller;

import com.realworld.wages.dto.dailyExpensiveDto;
import com.realworld.wages.entities.dailyExpensive;
import com.realworld.wages.mapper.dailyExpensiveMapper;
import com.realworld.wages.service.dailyExpensiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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
    public dailyExpensiveDto create (@PathVariable Long userId, @Validated @RequestBody dailyExpensiveDto expensiveDto) throws ParseException{
        dailyExpensive expensive = expenseMapper.mapToEntity(expensiveDto);
        return expenseMapper.mapToDto(expenseService.createExpensive(userId,expensive));
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = dailyExpensiveDto.class))),
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = dailyExpensiveDto.class))) })
    @GetMapping(value = "/getById/{userId}/{expenseId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public CollectionModel<dailyExpensiveDto> getByuserIdExpenseId(@PathVariable Long userId, @PathVariable Long expenseId) throws ParseException{
        List<dailyExpensive> expense = expenseService.getExpensiveById(userId, expenseId);
        List<dailyExpensiveDto> expenseDto = expense.stream().map(t ->{
            return expenseMapper.mapToDto(t);
        }).toList();

        return CollectionModel.of(expenseDto);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = dailyExpensiveDto.class))),
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = dailyExpensiveDto.class))) })
    @GetMapping(value = "/getAll/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public CollectionModel<dailyExpensiveDto> getExpensesByUserId(@PathVariable Long userId) throws ParseException{
        List<dailyExpensive> expense = expenseService.getExpensiveByUserId(userId);
        List<dailyExpensiveDto> expenseDto = expense.stream().map(t ->{
            return expenseMapper.mapToDto(t);
        }).toList();

        return CollectionModel.of(expenseDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = "/put/{expenseId}/{userId}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public dailyExpensiveDto put(@PathVariable Long expenseId, @PathVariable Long userId, @RequestBody dailyExpensiveDto expenseDto) throws ParseException {
        dailyExpensive expensive = expenseService.put(expenseId, userId, expenseDto);
        return expenseMapper.mapToDto(expensive);
    }
}
