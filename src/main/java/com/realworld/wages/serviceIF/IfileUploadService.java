package com.realworld.wages.serviceIF;

import com.realworld.wages.entities.fileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IfileUploadService {

    /**
     *
     * @param file
     * @return
     */
    fileUpload uploadImageToFileSystem(MultipartFile file, Long expenseId) throws IOException;


    /**
     *
     * @param fileName
     * @return
     */

    fileUpload downloadImageFromFile(String fileName) throws IOException;


    /**
     *
     * @param file
     * @param expenseId
     * @return
     * @throws IOException
     *
     */

    fileUpload updateImageForExpense(MultipartFile file, Long expenseId) throws IOException;

}
