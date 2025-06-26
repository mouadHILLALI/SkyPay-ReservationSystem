package com.booking.model;

import java.util.List;
import java.util.UUID;

public class Room {
    private UUID publicId;
    private Long privateId;
    private int price;
    private RoomType type;
    private List<Booking> bookings;

    private Room(Builder builder){
        this.publicId = builder.publicId;
        this.privateId = builder.privateId;
        this.price = builder.price;
        this.type = builder.type;
        this.bookings = builder.bookings;
    }

    public UUID getPublicId(){
        return this.publicId;
    }

     public Long getPrivateId(){
        return this.privateId;
    }

     public int getPrice(){
        return this.price;
    }

     public RoomType getType(){
        return this.type;
    }

     public List<Booking> getBookings(){
        return this.bookings;
    }


  public static class Builder {
    private UUID publicId;
    private Long privateId;
    private int price;
    private RoomType type;
    private List<Booking> bookings;

    public Builder(UUID publicId, Long privateId) {
        this.publicId = publicId;
        this.privateId = privateId;
    }

    public Builder price(int price) {
        this.price = price;
        return this;
    }

    public Builder type(RoomType type) {
        this.type = type;
        return this;
    }

    public Builder bookings(List<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Room build() {
        return new Room(this);
    }
}
}
