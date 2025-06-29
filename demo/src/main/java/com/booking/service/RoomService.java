package com.booking.service;

import com.booking.model.Room;
import com.booking.model.RoomType;

public interface RoomService {
    void createRoom(RoomType type , int price);
    void deleteRoom(Long privateId);
    Room getRoomById(Long privateId);
    void printAll();
}
