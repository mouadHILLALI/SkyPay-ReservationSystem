package com.booking.service;

import java.util.UUID;

import com.booking.model.User;

public interface UserService {
    void createUser(int balance);
    void deleteUser(Long privateId);
    void printAll();
    User getUserByPrivateID(Long privateID); 
}
