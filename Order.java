import java.util.Date;
import java.text.SimpleDateFormat;

public class Order {
    private static int lastOrderID = 0;

    private int orderID;
    private String date;
    private int menuID;
    private int quantity;

    public Order(int menuID, int quantity) {
        // Constructor for new order logic
    }

    public Order(int orderID, String date, int menuID, int quantity) {
        // Constructor for existing order logic
    }

    // Getter methods for orderID, date, menuID, and quantity
}
