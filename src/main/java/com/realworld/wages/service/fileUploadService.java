package com.realworld.wages.service;

import com.realworld.wages.entities.dailyExpensive;
import com.realworld.wages.entities.fileUpload;
import com.realworld.wages.repository.dailyExpensiveRepository;
import com.realworld.wages.repository.fileUploadRepository;
import com.realworld.wages.serviceIF.IfileUploadService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class fileUploadService implements IfileUploadService {

    /**
     * Author Akhilesh
     *
     */

    @Autowired
    private fileUploadRepository fileRepo;

    @Autowired
    private dailyExpensiveRepository expenseRepo;


    private final String FOLDER_PATH="D:\\FileUpload";

    public fileUpload uploadImageToFileSystem(MultipartFile file, Long expenseId) throws IOException {
        dailyExpensive expense = expenseRepo.findById(expenseId).orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + expenseId));

        Files.createDirectories(Paths.get(FOLDER_PATH));

        String OrignalFileName= file.getOriginalFilename();
        String extention = "";

        if (OrignalFileName != null && OrignalFileName.contains(".")) {
            extention = OrignalFileName.substring(OrignalFileName.lastIndexOf("."));
        }

        String newFileName = expenseId + extention;
        String filePath = Paths.get(FOLDER_PATH, newFileName).toString();

        fileUpload fileData = fileRepo.save(fileUpload.builder()
                .name(newFileName)
                .type(file.getContentType())
                .filePath(filePath)
                .expenseId(expense.getExpensesId())
                .build());

        file.transferTo(new File(filePath));

        expense.setName(newFileName);
        expense.setFilePath(filePath);
        expenseRepo.save(expense);

        return fileData;
    }

    public fileUpload downloadImageFromFile(String fileName) throws IOException {
        fileUpload file = fileRepo.findByName(fileName)
                .orElseThrow(() -> new FileNotFoundException("File not found: " + fileName));
        byte[] imageBytes = Files.readAllBytes(Paths.get(file.getFilePath()));

        file.setImageData(imageBytes);
        return file;
    }

    public fileUpload updateImageForExpense(MultipartFile file, Long expenseId) throws IOException {
        dailyExpensive expense = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + expenseId));

        Optional<fileUpload> existingFileOpt = fileRepo.findByExpenseId(expenseId);

        String originalFileName = file.getOriginalFilename();
        String extension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String newFileName = expenseId + extension;
        String filePath = Paths.get(FOLDER_PATH, newFileName).toString();

        if (existingFileOpt.isPresent()) {
            File oldFile = new File(existingFileOpt.get().getFilePath());
            if (oldFile.exists()) {
                oldFile.delete();
            }
        }

        file.transferTo(new File(filePath));

        fileUpload fileData = existingFileOpt.orElse(new fileUpload());
        fileData.setName(newFileName);
        fileData.setType(file.getContentType());
        fileData.setFilePath(filePath);
        fileData.setExpenseId(expenseId);

        expense.setName(newFileName);
        expense.setFilePath(filePath);
        expenseRepo.save(expense);


        return fileRepo.save(fileData);
    }

}
