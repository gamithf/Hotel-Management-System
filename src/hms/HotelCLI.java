package hms;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class HotelCLI {
    private final Hotel hotel;
    private final Scanner scanner;

    public HotelCLI() {
        hotel = Hotel.getInstance();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("------------ Welcome to Hotel Management System ------------");

        // available rooms
        hotel.addRoom(RoomFactory.getRoom(101, "Single"));
        hotel.addRoom(RoomFactory.getRoom(102, "Double"));
        hotel.addRoom(RoomFactory.getRoom(103, "Suite"));

        while (true) {
            System.out.println("1. Book Room");
            System.out.println("2. Check Out");
            System.out.println("3. Exit");

            System.out.print("|| Enter: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookRoom();
                    break;
                case 2:
                    checkOut();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void bookRoom() {
        System.out.println("--------- Book Room ---------");

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        if (validateRoom(roomNumber)) {
            if (Objects.requireNonNull(getRoomByNumber(roomNumber)).isAvailable()) {
                System.out.print("Select guest type (1- guest, 2- vip): ");
                int type = scanner.nextInt();
                Guest guest;
                if (type == 1) {
                    guest = new Guest(Hotel.getCountOfGuests()+1, "gamithf", "12345");
                } else if (type == 2) {
                    guest = new VIPGuest(Hotel.getCountOfGuests()+1, "sandeep", "00000");
                } else {
                    System.out.println("Invalid guest type");
                    return;
                }
                hotel.bookRoom(guest, Objects.requireNonNull(getRoomByNumber(roomNumber)));
            } else
                System.out.println("Room is already booked");
        } else
            System.out.println("Invalid room number");
    }

    private void checkOut() {
        System.out.println("--------- Checkout Room ---------");

        System.out.print("Enter guest number: ");
        int guestNumber = scanner.nextInt();

        if (validateGuest(guestNumber)) {
            ArrayList<Room> ownedRooms = Hotel.displayAndGetRoomsBookedByGuest(getGuestByNumber(guestNumber));
            double payment = 0;

            do {
                if (ownedRooms.isEmpty())
                    break;

                System.out.print("Enter room for checking out: ");
                int checkoutRoom = scanner.nextInt();

                if (ownedRooms.contains(getRoomByNumber(checkoutRoom))) {
                    hotel.clearRoom(getGuestByNumber(guestNumber), getRoomByNumber(checkoutRoom));
                    payment = Objects.requireNonNull(getRoomByNumber(checkoutRoom)).getPrice() - Objects.requireNonNull(getGuestByNumber(guestNumber)).calculateDiscount(Objects.requireNonNull(getRoomByNumber(checkoutRoom)).getPrice());

                    System.out.print("Checking out more? (1- Yes, 2- No): ");
                    int continuing = scanner.nextInt();
                    if (continuing == 2)
                        break;

                } else {
                    System.out.println("Provided room is now owned by the guest. Try again " + guestNumber);
                }
            } while(true);

            if (payment == 0)
                System.out.println("No rooms owned by the user " + guestNumber);
            else
                System.out.println("Total payment: Rs. " + payment);
        } else {
            System.out.println("Invalid guest number");
        }
    }

    private Room getRoomByNumber(int roomNumber) {
        ArrayList<Room> rooms = hotel.getRooms();
        for (Room room: rooms) {
            if (room.getRoomNumber() == roomNumber)
                return room;
        }
        return null;
    }

    private boolean validateRoom(int roomNumber) {
        ArrayList<Room> rooms = hotel.getRooms();
        for (Room room: rooms) {
            if (room.getRoomNumber() == roomNumber)
                return true;
        }
        return false;
    }

    private boolean validateGuest(int guestNumber) {
        ArrayList<Guest> guests = hotel.getGuests();
        for (Guest guest: guests) {
            if (guest.getGuestNumber() == guestNumber)
                return true;
        }
        return false;
    }

    private Guest getGuestByNumber(int guestNumber) {
        ArrayList<Guest> guests = hotel.getGuests();
        for (Guest guest: guests) {
            if (guest.getGuestNumber() == guestNumber)
                return guest;
        }
        return null;
    }
}
