package com.booking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID publicId;
    private Long privateId;
    private int balance;
    private List<Booking> bookings = new ArrayList<>();

    private User(Builder builder) {
        this.publicId = builder.publicId;
        this.privateId = builder.privateId;
        this.balance = builder.balance;
        this.bookings = (builder.bookings != null) ? builder.bookings : new ArrayList<>();
    }

    public UUID getPublicId() {
        return this.publicId;
    }

    public Long getPrivateId() {
        return this.privateId;
    }

    public int getBalance() {
        return this.balance;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public void addBookings(Booking booking){
        this.bookings.add(booking);
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
    public static class Builder {
        private UUID publicId;
        private Long privateId;
        private int balance;
        private List<Booking> bookings;

        public Builder() {
        }

        public Builder balance(int balance) {
            this.balance = balance;
            return this;
        }

        public Builder publicId(UUID publicID) {
            this.publicId = publicID;
            return this;
        }

        public Builder privateId(Long privateId) {
            this.privateId = privateId;
            return this;
        }

        public Builder bookings(List<Booking> bookings) {
            this.bookings = bookings;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
