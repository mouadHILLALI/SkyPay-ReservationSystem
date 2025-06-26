package com.booking.model;

import java.util.List;
import java.util.UUID;

public class User {
    private UUID publicId;
    private Long privateId;
    private int balance;
    private List<Booking> bookings;
}
