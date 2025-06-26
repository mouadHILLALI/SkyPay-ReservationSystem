package com.booking.model;

import java.sql.Date;
import java.util.UUID;

public class Booking {

    private UUID publicId;
    private Long privateId;
    private Room room;
    private User user;
    private Date bookingStartDate;
    private Date bookingEndDate;
    
}
