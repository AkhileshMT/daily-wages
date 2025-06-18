package com.realworld.wages.controller;


import com.realworld.wages.dto.categoryDto;
import com.realworld.wages.dto.storeEarningDto;
import com.realworld.wages.entities.category;
import com.realworld.wages.mapper.categoryMapper;
import com.realworld.wages.service.categoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class categoryResource {

    @Autowired
    private categoryService catService;

    @Autowired
    private categoryMapper catMapper;


    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = categoryDto.class))),
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = categoryDto.class)))})
    @GetMapping(value = "/all",produces = { MediaType.APPLICATION_JSON_VALUE })
    public CollectionModel<categoryDto> findAllCategory() throws ParseException {

        List<category> cat = catService.findAllCategory();
        List<categoryDto> catDto = cat.stream().map(t ->{
            return catMapper.mapToDto(t);
        }).toList();

        return  CollectionModel.of(catDto);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = categoryDto.class))),
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = categoryDto.class)))})
    @GetMapping(value = "/get/{categoryId}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public CollectionModel<categoryDto> getByCategoryId(@PathVariable Long categoryId) throws ParseException{
        List<category> cat = catService.getCategoryById(categoryId);
        List<categoryDto> catDto = cat.stream().map(t ->{
            return catMapper.mapToDto(t);
        }).toList();
        return CollectionModel.of(catDto);
    }

    @PostMapping(value = "/create/{userId}",  produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public categoryDto create(@PathVariable Long userId, @Validated @RequestBody categoryDto catDto) throws ParseException {

        category cat = catMapper.mapToEntity(catDto);

        return catMapper.mapToDto(catService.createCategory(userId,cat));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = "/put/{categoryId}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public categoryDto updateCategory(@PathVariable Long categoryId, @Validated @RequestBody categoryDto catDto) throws ParseException{
        category cat = catService.updateCategory(categoryId, catDto);
        return catMapper.mapToDto(cat);
    }

}
