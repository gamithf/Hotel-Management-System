package hms;

public class Guest implements Observer {
    private int guestNumber;
    private String name;
    private String contact;

    public Guest(int guestID, String name, String contact) {
        this.guestNumber = guestID;
        this.name = name;
        this.contact = contact;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Guest ID: " + guestNumber + ", Name: " + name + ", Contact: " + contact;
    }

    public double calculateDiscount(double billAmount) {
        return 0.0;
    }

    @Override
    public void notifyRoomAvailability(Room room) {
        System.out.println(room.getRoomNumber() + " " + room.getRoomType() + " is ready");
    }
}
