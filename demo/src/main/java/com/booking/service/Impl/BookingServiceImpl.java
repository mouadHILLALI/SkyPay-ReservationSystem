package com.booking.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.booking.model.Booking;
import com.booking.model.Room;
import com.booking.model.User;
import com.booking.service.BookingService;
import com.booking.service.RoomService;
import com.booking.service.UserService;

public class BookingServiceImpl implements BookingService {
    private RoomService roomService;
    private UserService userService;
    private final List<Booking> bookings = new ArrayList<>();

    public BookingServiceImpl(RoomService roomService , UserService userService){
        this.roomService = roomService;
        this.userService = userService;
    }

    public void bookRoom(Long userId , Long roomId , Date starDate , Date endDate){
        User user = userService.getUserByPrivateID(userId);
        Room room = roomService.getRoomById(roomId);
        Booking booking = new Booking.Builder().privateId(generatePrivateIds()).publicId(UUID.randomUUID()).build();
        if (isBookingDateValid(starDate, endDate, room) && isBalanceValid(user , room , starDate , endDate)) {
            booking.setStartDate(starDate);
            booking.setEndDate(endDate);
            bookings.add(booking);
            user.addBookings(booking);
            user.setBalance(user.getBalance() - calcAmountToPay(booking, room));
            room.addBookings(booking);
            System.out.println("The room was booked succesfully");
            printBooking(booking, room);
        }else {
            System.out.println("unable to book the room, Invalid booking dates or balance");
        }
    };

    public void cancelBooking(Long privateId){

    };

    public boolean isBookingDateValid(Date startDate, Date endDate, Room room) {
    Date now = new Date();

    if (startDate == null || endDate == null || !startDate.before(endDate) || startDate.before(now)) return false;
    if (room.getBookings() == null || room.getBookings().isEmpty()) return true;
        for (Booking booking : room.getBookings()) {
        Date existingStart = booking.getBookingStartDate();
        Date existingEnd = booking.getBookingEndDate();
        boolean overlap = !(endDate.before(existingStart) || startDate.after(existingEnd));
        if (overlap) {
            return false;
        }
    }
    return true;
}

    public boolean isBalanceValid(User user, Room room, Date startDate, Date endDate) {
        if (user == null || room == null || startDate == null || endDate == null || !startDate.before(endDate)) {
            return false;
        }

        long diffInMillies = endDate.getTime() - startDate.getTime();
        int nightsToBeBooked = (int) (diffInMillies / (1000 * 60 * 60 * 24)); 

        int priceToPay = nightsToBeBooked * room.getPrice();
        return user.getBalance() >= priceToPay;
    }

    private void printBooking(Booking booking , Room room){
        System.out.println("Booking Details:\n");
        System.out.println("Public ID:\n " + booking.getPublicId());
        System.out.println("Private ID:\n " + booking.getPrivateId());
        System.out.println("Amount to pay:\n " + calcAmountToPay(booking, room));
        System.out.println("StartDate:\n " + booking.getBookingStartDate());
        System.out.println("EndDate:\n " + booking.getBookingEndDate());
    }

    private int calcAmountToPay(Booking booking , Room room) throws ArithmeticException {
        try {
        long diffInMillies = booking.getBookingEndDate().getTime() - booking.getBookingStartDate().getTime();
        int nightsToBeBooked = (int) (diffInMillies / (1000 * 60 * 60 * 24)); 
        return nightsToBeBooked * room.getPrice();
        } catch (ArithmeticException e) {
            throw new ArithmeticException();
        }
    }
   
    public void printAll(){
        System.out.println("List of bookings : \n");
        for(Booking booking : bookings){ 
            printBooking(booking, booking.getRoom());
        }
    };

    private Long generatePrivateIds(){
        return (long) (bookings.size() + 1);
    }

    
}
