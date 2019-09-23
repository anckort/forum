package com.example.forum.service;

import com.example.forum.entity.User;

public interface UserService {
    void save(User user);
    User authorisation(String username, String password);
    User findById(Integer id);
    void delete(User user);
}
