# SkyPay-ReservationSystem

# Booking System Application
Overview
This is a simple console-based Booking System application implemented in Java. It allows management of users, rooms, and bookings. The system supports:

Creating and listing users with balances.

Creating and listing rooms with types and prices.

Booking rooms for users with date validation and conflict checks.

Input validation and safe console interaction.

The project follows a modular architecture with separate services for user, room, and booking management, and controllers for handling user input.

Features
User Management: Create users with initial balance, list all users.

Room Management: Create rooms with predefined room types (Standard Suite, Junior Suite, Master Suite), and price per night; list all rooms.

Booking Management: Book a room for a user by specifying user ID, room ID, and booking dates; list all bookings.

Input Validation: Robust handling of invalid input with graceful error messages.

Booking Date Validation: Checks to prevent overlapping bookings and invalid date ranges.

Technologies
Java 8+

Maven (build and dependency management)

Console interface with Scanner input

Project Structure
model package: Contains entities like User, Room, Booking, and enums like RoomType.

service package: Contains business logic split into UserService, RoomService, and BookingService.

controller package: Console UI and input handling in MenuController and internal controllers.

App.java: Entry point to launch the application.

# Answers to Questions
1. Suppose we put all the functions inside the same service. Is this the recommended approach? Please explain.
Answer:

Putting all functions related to users, rooms, and bookings inside the same service is generally not recommended because it violates the Single Responsibility Principle — a core concept in clean architecture and maintainability.

In your project, you correctly separated concerns into three services:

UserService handles user creation and management.

RoomService handles room creation and listing.

BookingService manages booking operations and booking validations.

This separation improves:

Modularity: Each service has a clear, focused responsibility.

Maintainability: Easier to locate and modify code related to a particular domain.

Testability: Individual services can be tested independently.

Scalability: If the system grows, different teams can work on distinct services.

Combining all functionality in a single service would lead to a bloated class, harder to maintain and extend.

2. In this design, we chose to have a function setRoom(..) that should not impact the previous bookings. What is another way? What is your recommendation? Please explain and justify.
Answer:

Your current design assumes that modifying the Room details (like price or type) does not affect existing bookings for that room. This is reasonable because:

Bookings are snapshots tied to the room at booking time.

Changing room info afterward should not retroactively alter historical bookings.

Another approach could be:

Immutable Room Details for Bookings: Store room details inside each booking as immutable fields (e.g., room type and price at booking time) so that even if the room is updated later, the booking info remains accurate.

Versioning Rooms: Implement version control for rooms. Each booking refers to a specific room version. Changes create a new version without affecting old bookings.

# Recommendation:

I recommend keeping room updates independent from previous bookings as you do, but storing the relevant room details in the booking entity at the time of booking (price, type). This way:

You maintain historical accuracy for bookings.

Future room updates won't break old booking data.

Reporting and billing remain consistent.