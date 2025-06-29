package com.booking.controller;

import com.booking.model.RoomType;
import com.booking.service.BookingService;
import com.booking.service.RoomService;
import com.booking.service.UserService;
import java.sql.Date;
import java.util.Scanner;

public class MenuController {
    private final UserService userService;
    private final RoomService roomService;
    private final BookingService bookingService;
    private final Scanner scanner;
    private final UserController userController;
    private final RoomController roomController;
    private final BookingController bookingController;

    public MenuController(UserService userService, RoomService roomService, BookingService bookingService, Scanner scanner) {
        this.userService = userService;
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.scanner = scanner;
        this.userController = new UserController();
        this.roomController = new RoomController();
        this.bookingController = new BookingController();
    }

  public void mainMenu() {
        while (true) {
            System.out.println("\n=== Welcome to the Booking App ===");
            System.out.println("1 - Manage users");
            System.out.println("2 - Manage rooms");
            System.out.println("3 - Manage bookings");
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    manageUsers();
                    break;
                case "2":
                    manageRooms();
                    break;
                case "3":
                    manageBookings();
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageUsers() {
        while (true) {
            System.out.println("\nUser Management Menu");
            System.out.println("1 - Add New User");
            System.out.println("2 - Print All Users");
            System.out.println("0 - Return to Main Menu");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return;  
            }

            switch (input) {
                case "1":
                    userController.addUser(scanner, userService);
                    break;
                case "2":
                    userController.printAllUsers(userService);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageRooms() {
        while (true) {
            System.out.println("\nRoom Management Menu");
            System.out.println("1 - Add New Room");
            System.out.println("2 - List All Rooms");
            System.out.println("0 - Return to Main Menu");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return;
            }

            switch (input) {
                case "1":
                    roomController.addRoom(scanner, roomService);
                    break;
                case "2":
                    roomController.printAllRooms(roomService);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageBookings() {
        while (true) {
            System.out.println("\nBooking Management Menu");
            System.out.println("1 - Book a Room");
            System.out.println("2 - List All Bookings");
            System.out.println("0 - Return to Main Menu");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return; 
            }

            switch (input) {
                case "1":
                    bookingController.addBooking(scanner, bookingService);
                    break;
                case "2":
                    bookingController.printAll(bookingService);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private class UserController {
        public void addUser(Scanner sc, UserService userService) {
            while (true) {
                System.out.print("Enter the user balance (or 0 to cancel): ");
                String input = sc.nextLine().trim();
                if (input.equals("0")) {
                    System.out.println("Cancelled adding user.");
                    return;
                }
                try {
                    int balance = Integer.parseInt(input);
                    if (balance < 0) {
                        System.out.println("Balance cannot be negative.");
                        continue;
                    }
                    userService.createUser(balance);
                    System.out.println("User created successfully.");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid integer.");
                }
            }
        }

        public void printAllUsers(UserService userService) {
            userService.printAll();
        }
    }

    private class RoomController {
        public void addRoom(Scanner sc, RoomService roomService) {
            RoomType roomType = null;
            while (roomType == null) {
                System.out.println("Enter the room type:");
                System.out.println("1 - Standard Suite");
                System.out.println("2 - Junior Suite");
                System.out.println("3 - Master Suite");
                System.out.println("0 - Cancel");
                System.out.print("Choice: ");
                String input = sc.nextLine().trim();
                switch (input) {
                    case "1":
                        roomType = RoomType.STANDARD_SUITE;
                        break;
                    case "2":
                        roomType = RoomType.JUNIOR_SUITE;
                        break;
                    case "3":
                        roomType = RoomType.MASTER_SUITE;
                        break;
                    case "0":
                        System.out.println("Cancelled adding room.");
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            }

            while (true) {
                System.out.print("Please enter the room price (per night): ");
                String priceInput = sc.nextLine().trim();
                if (priceInput.equals("0")) {
                    System.out.println("Cancelled adding room.");
                    return;
                }
                try {
                    int price = Integer.parseInt(priceInput);
                    if (price < 0) {
                        System.out.println("Price cannot be negative.");
                        continue;
                    }
                    roomService.createRoom(roomType, price);
                    System.out.println("Room created successfully.");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price. Please enter a valid integer.");
                }
            }
        }

        public void printAllRooms(RoomService roomService) {
            roomService.printAll();
        }
    }

    private class BookingController {
        public void addBooking(Scanner sc, BookingService bookingService) {
            Long userId = null;
            Long roomId = null;

            while (userId == null) {
                System.out.print("Enter the user ID (or 0 to cancel): ");
                String input = sc.nextLine().trim();
                if (input.equals("0")) {
                    System.out.println("Cancelled booking.");
                    return;
                }
                try {
                    userId = Long.parseLong(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid user ID. Please enter a valid number.");
                }
            }

            while (roomId == null) {
                System.out.print("Enter the room ID (or 0 to cancel): ");
                String input = sc.nextLine().trim();
                if (input.equals("0")) {
                    System.out.println("Cancelled booking.");
                    return;
                }
                try {
                    roomId = Long.parseLong(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid room ID. Please enter a valid number.");
                }
            }

            Date startDate = null;
            Date endDate = null;

            while (startDate == null) {
                System.out.print("Enter the booking start date (yyyy-mm-dd) or 0 to cancel: ");
                String input = sc.nextLine().trim();
                if (input.equals("0")) {
                    System.out.println("Cancelled booking.");
                    return;
                }
                try {
                    startDate = Date.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                }
            }

            while (endDate == null) {
                System.out.print("Enter the booking end date (yyyy-mm-dd) or 0 to cancel: ");
                String input = sc.nextLine().trim();
                if (input.equals("0")) {
                    System.out.println("Cancelled booking.");
                    return;
                }
                try {
                    endDate = Date.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                }
            }

            bookingService.bookRoom(userId, roomId, startDate, endDate);
            System.out.println("Booking successful.");
        }

        public void printAll(BookingService bookingService) {
            bookingService.printAll();
        }
    }
}
