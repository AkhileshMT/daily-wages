package com.realworld.wages.mapper;

import com.realworld.wages.dto.fileUploadDto;
import com.realworld.wages.entities.fileUpload;
import org.springframework.stereotype.Component;

@Component
public class fileUploadMapper {


    public fileUploadDto mapToDto(fileUpload upload){
        fileUploadDto uploadDto = new fileUploadDto();

        uploadDto.setName(upload.getName());
        uploadDto.setFilePath(upload.getFilePath());
        uploadDto.setType(upload.getType());
        uploadDto.setExpenseId(upload.getExpenseId());

        return uploadDto;
    }


    public fileUpload matToEntity(fileUploadDto uploadDto){
        fileUpload upload = new fileUpload();

        upload.setName(uploadDto.getName());
        upload.setType(uploadDto.getType());
        upload.setFilePath(uploadDto.getFilePath());
        upload.setExpenseId(uploadDto.getExpenseId());

        return upload;
    }

}
