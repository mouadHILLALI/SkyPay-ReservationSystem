package com.booking.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.booking.model.Booking;
import com.booking.model.Room;
import com.booking.model.RoomType;
import com.booking.service.RoomService;

public class RoomServiceImpl implements RoomService {

    private final List<Room> rooms = new ArrayList<>();

    public RoomServiceImpl(){}

    @Override
    public void createRoom(RoomType type , int price){
        Room room = new Room.Builder(UUID.randomUUID(), generatePrivateId()).type(type).price(price).build();
        rooms.add(room);
        System.out.println("Room created succesfully :");
        printRoom(room);
    }
    @Override
    public void deleteRoom(Long privateId){
            rooms.remove(privateId);
            System.out.println("Room deleted succesfully");
        System.out.println("No room was found with this Id :" + privateId);

    }

    @Override
   public Room getRoomById(Long id) {
    for (Room room : rooms) {
        if (room.getPrivateId().equals(id)) {  
            return room;
        }
    }
    return null;  
}

    @Override
    public void printAll(){
        for(Room room : rooms){
            printRoom(room);
        }
    }
    private Long generatePrivateId(){
        return (long) (rooms.size() + 1);
    }
    private void printRoom(Room room){
        System.out.println("Room Details:");
        System.out.println("Public ID: " + room.getPublicId());
        System.out.println("Private ID: " + room.getPrivateId());
        System.out.println("Price: " + room.getPrice());
        System.out.println("Type: " + room.getType());
        System.out.println("Bookings:");
        if (room.getBookings() != null && !room.getBookings().isEmpty()) {
            for (Booking booking : room.getBookings()) {
                System.out.println("- " + booking); 
            }
        } else {
            System.out.println("- No bookings");
        }
    }
}
