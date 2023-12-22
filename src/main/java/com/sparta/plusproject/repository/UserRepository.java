package com.sparta.plusproject.repository;

import com.sparta.plusproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
