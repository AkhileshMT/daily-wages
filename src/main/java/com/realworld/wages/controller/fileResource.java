package com.realworld.wages.controller;

import com.realworld.wages.dto.fileUploadDto;
import com.realworld.wages.entities.fileUpload;
import com.realworld.wages.mapper.fileUploadMapper;
import com.realworld.wages.service.fileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image")
public class fileResource {

    @Autowired
    private fileUploadMapper uploadMapper;

    @Autowired
    private fileUploadService uploadService;


    @PostMapping(value= "/fileSystem/{expenseId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public fileUploadDto uploadImageToFIleSystem(@RequestParam("image")MultipartFile file, @PathVariable("expenseId") Long expenseId) throws IOException{
        fileUpload upload = uploadService.uploadImageToFileSystem(file, expenseId);
        return uploadMapper.mapToDto(upload);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = fileUploadDto.class))),
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = fileUploadDto.class))) })
    @GetMapping(value = "/fileSystem/{fileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        fileUpload file = uploadService.downloadImageFromFile(fileName);
        byte[] imageBytes = Files.readAllBytes(Paths.get(file.getFilePath()));
        MediaType mediaType = MediaType.parseMediaType(file.getType());
        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(imageBytes);
    }

    @PutMapping(value= "/updateImage/{expenseId}")
    @ResponseBody
    public ResponseEntity<?> updateImage(@RequestParam("image")MultipartFile file, @PathVariable("expenseId") Long expenseId){
        try {
            fileUpload updatedFile = uploadService.updateImageForExpense(file, expenseId);
            return ResponseEntity.ok(updatedFile);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File update failed");
        }
    }







}
