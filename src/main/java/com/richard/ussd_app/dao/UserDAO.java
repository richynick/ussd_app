package com.richard.ussd_app.dao;

import com.richard.ussd_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
