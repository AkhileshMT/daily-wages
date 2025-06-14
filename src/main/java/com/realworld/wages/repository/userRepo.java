package com.realworld.wages.repository;

import com.realworld.wages.entities.storeEarning;
import com.realworld.wages.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<users,Long> {
    Optional<users> findByUserName(String userName);



}
