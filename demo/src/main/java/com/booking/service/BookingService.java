package com.booking.service;


import java.util.Date;

import com.booking.model.Room;
import com.booking.model.User;

public interface BookingService {
    void bookRoom(Long userId , Long roomId, Date starDate , Date endDate);
    void cancelBooking(Long privateId);
    boolean isBookingDateValid(Date startDate , Date endDate , Room room);
    boolean isBalanceValid(User user, Room room, Date startDate, Date endDate);
    void printAll();
}
