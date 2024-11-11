package hms;

public class VIPGuest extends Guest{

    public VIPGuest(int guestID, String name, String contact) {
        super(guestID, name, contact);
    }

    @Override
    public double calculateDiscount(double billAmount) {
        return billAmount * 0.1;
    }
}
