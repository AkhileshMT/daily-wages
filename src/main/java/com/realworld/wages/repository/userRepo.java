package com.realworld.wages.repository;

import com.realworld.wages.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<users,Integer> {

}
