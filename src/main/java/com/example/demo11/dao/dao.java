package com.example.demo11.dao;

import com.example.demo11.Model.User;

import java.util.List;

public interface dao {
    void saveUser(User user);
    List<User> Lister();
    boolean checkLogin(String username, String password);
    boolean isEmailExists(String email);
    void deleteUser(int id);
    void updateUser(int id, User updatedUser);
}
