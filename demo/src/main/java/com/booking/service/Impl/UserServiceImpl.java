package com.booking.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.booking.model.Booking;
import com.booking.model.Room;
import com.booking.model.User;
import com.booking.service.UserService;

public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    public UserServiceImpl(){}

    @Override
    public void createUser(int balance){
        User newUser = new User.Builder().privateId(getPrivateIds()).publicId(UUID.randomUUID()).balance(balance).build();
        users.add(newUser);
        printUser(newUser);
    }

    @Override
    public void deleteUser(Long privateId){
        System.out.println("no user found with this id " + privateId);
    }
    @Override
    public void printAll(){
        System.out.println("existing users :");
        for(User user : users){
            System.out.println("User Details:");
            System.out.println("Public ID: " + user.getPublicId());
            System.out.println("Private ID: " + user.getPrivateId());
            System.out.println("Balance: " + user.getBalance());
            System.out.println("Bookings:");
        }
    }

    private Long getPrivateIds(){
        return (long) (users.size() + 1);
    }

   private void printUser(User user) {
    System.out.println("User Details:");
    System.out.println("Public ID: " + user.getPublicId());
    System.out.println("Private ID: " + user.getPrivateId());
    System.out.println("Balance: " + user.getBalance());
    System.out.println("Bookings:");
    if (user.getBookings() != null && !user.getBookings().isEmpty()) {
        for (Booking booking : user.getBookings()) {
            System.out.println("- " + booking); 
        }
    } else {
        System.out.println("- No bookings");
    }
}

   @Override
   public User getUserByPrivateID(Long privateID) {
    for (User user : users) {
            if (user.getPrivateId().equals(privateID)) {  
                return user;
            }
        }
        return null;  
   }
}
