package hms;

import java.util.ArrayList;
import java.util.HashMap;

public class Hotel {
    private static Hotel instance;
    private ArrayList<Room> rooms;
    private static ArrayList<Guest> guests;
    private static HashMap<Guest, ArrayList<Room>> bookedRooms;

    private Hotel() {
        this.rooms = new ArrayList<>();
        guests = new ArrayList<>();
        bookedRooms = new HashMap<>();
    }

    public static Hotel getInstance() {
        if (instance == null) {
            instance = new Hotel();
        }
        return instance;
    }

    public static  ArrayList<Room> displayAndGetRoomsBookedByGuest(Guest guest) {
        Guest guest1 = guest;
        ArrayList<Room> roomsForGuest = bookedRooms.get(guest1);

        if (roomsForGuest != null && !roomsForGuest.isEmpty()) {
            System.out.println("Rooms booked for " + guest1.getName() + " -guest no: " + guest1.getGuestNumber() + " :"); // Assuming Guest class has getName() method
            for (Room room : roomsForGuest) {
                System.out.println(" -  " + room.getRoomNumber()); // Assuming Room class has getRoomNumber() method
            }
        } else {
            System.out.println(guest1.getName() + " has no rooms booked.");
        }

        return roomsForGuest;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public HashMap<Guest, ArrayList<Room>> getBookedRooms() {
        return bookedRooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public void bookRoom(Guest guest, Room room) {
        if (room.isAvailable()) {
            guests.add(guest);
            bookedRooms.computeIfAbsent(guest, k-> new ArrayList<>()).add(room);
            room.setAvailable(false);
            System.out.println("Room " + room.getRoomNumber() + " for guest " + guest.getGuestNumber() + " is booked successfully");
        } else
            System.out.println("Room is already booked");
    }

    public void clearRoom(Guest guest, Room room) {
        ArrayList<Room> rooms = bookedRooms.get(guest);

        if (rooms != null) {
            rooms.remove(room);
            room.setAvailable(true);
            if (rooms.isEmpty()) {
                bookedRooms.remove(guest);
            }
        }
    }

    public static int getCountOfGuests() {
        return guests.size();
    }

}
