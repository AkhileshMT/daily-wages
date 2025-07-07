package com.realworld.wages.repository;

import com.realworld.wages.entities.fileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface fileUploadRepository extends JpaRepository<fileUpload, Integer> {

    Optional<fileUpload> findByName(String fileName);

    Optional<fileUpload> findByExpenseId(Long expenseId);
}
