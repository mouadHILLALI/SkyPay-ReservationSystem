package com.booking;

import java.util.Scanner;

import com.booking.controller.MenuController;
import com.booking.service.BookingService;
import com.booking.service.RoomService;
import com.booking.service.UserService;
import com.booking.service.Impl.BookingServiceImpl;
import com.booking.service.Impl.RoomServiceImpl;
import com.booking.service.Impl.UserServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserService userService = new UserServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        BookingService bookingService = new BookingServiceImpl(roomService, userService);

        new MenuController(userService, roomService, bookingService, new Scanner(System.in)).mainMenu();
    }
}
