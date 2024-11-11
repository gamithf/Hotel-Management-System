package hms;

public class RoomFactory {
    public static Room getRoom(int roomNumber, String type) {
        return switch (type) {
            case "Single" -> new Room(roomNumber, "Single", 1000, true);
            case "Double" -> new Room(roomNumber, "Double", 2000, true);
            case "Suite" -> new Room(roomNumber, "Suite", 5000, true);
            default -> null;
        };
    }
}
