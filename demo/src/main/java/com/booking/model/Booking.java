package com.booking.model;


import java.util.Date;
import java.util.UUID;

public class Booking {

    private UUID publicId;
    private Long privateId;
    private Room room;
    private User user;
    private Date bookingStartDate;
    private Date bookingEndDate;
    
    private Booking(Builder builder){

    }

    public UUID getPublicId(){
        return this.publicId;
    }

    public Long getPrivateId(){
        return this.privateId;
    }

    public Room getRoom(){
        return this.room;
    }

    public User getUser(){
        return this.user;
    }

    public Date getBookingStartDate(){
        return this.bookingStartDate;
    }

    public Date getBookingEndDate(){
        return this.bookingEndDate;
    }

    public void setStartDate(Date bookingStartDate){
        this.bookingStartDate = bookingStartDate;
    }   
    public void setEndDate(Date bookingEndDate){
        this.bookingEndDate = bookingEndDate;
    }

    public static class Builder{
        private UUID publicId;
        private Long privateId;
        private Room room;
        private User user;
        private Date bookingStartDate;
        private Date bookingEndDate;

        public Builder(){}

        public Builder publicId(UUID publicId){
            this.publicId = publicId;
            return this;
        }

        public Builder privateId(Long privateId){
            this.privateId = privateId;
            return this;
        }

        public Builder room(Room room){
            this.room = room;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder bookingStartDate(Date bookingStartDate){
            this.bookingStartDate = bookingStartDate;
            return this;
        }

        public Builder bookingEndDate(Date bookingEndDate){
            this.bookingEndDate = bookingEndDate;
            return this;
        }

        public Booking build(){
            return new Booking(this);
        }
    }
}
