package com.realworld.wages.controller;

import com.realworld.wages.dto.storeEarningDto;
import com.realworld.wages.entities.storeEarning;
import com.realworld.wages.mapper.storeEarningMapper;
import com.realworld.wages.repository.storeEarningRepository;
import com.realworld.wages.service.storeEarningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/storeEarning")
public class storeEarningResource {

    @Autowired
    storeEarningService earningService;

    @Autowired
    storeEarningMapper earningMapper;

    @PostMapping(value = "/create/{userId}",  produces = {
        MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public storeEarningDto create (@PathVariable Long userId, @Validated @RequestBody storeEarningDto earningDto){
        storeEarning earning = earningMapper.mapToEntity(earningDto);
        return earningMapper.mapToDto(earningService.create(userId, earning));
    }


    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = storeEarningDto.class))),
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = storeEarningDto.class))) })
    @GetMapping(value = "/all",produces = { MediaType.APPLICATION_JSON_VALUE })
    public CollectionModel<storeEarningDto> findAllStoreEarning() throws ParseException {
        List<storeEarning> earning = earningService.findAllEarnings();
        List<storeEarningDto> earningSto = earning.stream().map(t ->{
            return earningMapper.mapToDto(t);
        }).toList();

        return CollectionModel.of(earningSto);
    }


    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = storeEarningDto.class))),
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = storeEarningDto.class))) })
    @GetMapping(value = "/{userId}/{storeEarningId}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public CollectionModel<storeEarningDto> findStoreEarningByID(@PathVariable Long userId, @PathVariable Long storeEarningId) throws ParseException {
        List<storeEarning> earning = earningService.findByIDEarningId(userId, storeEarningId);
        List<storeEarningDto> earningDto = earning.stream().map(t ->{
            return earningMapper.mapToDto(t);
        }).toList();

        return CollectionModel.of(earningDto);
    }















}
