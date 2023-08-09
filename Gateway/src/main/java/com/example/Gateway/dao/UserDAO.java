package com.example.Gateway.dao;

import com.example.Gateway.entites.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
