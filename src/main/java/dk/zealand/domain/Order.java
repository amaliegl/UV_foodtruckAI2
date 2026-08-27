package dk.zealand.domain;

public class Order {
    private static final String STATUS_RECEIVED = "MODTAGET";

    private final long id;
    private final Dish dish;
    private final int quantity;
    private final String status;

    public Order(long id, Dish dish, int quantity) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
        this.status = STATUS_RECEIVED;
    }

    public long getId() {
        return id;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Bestilling #" + id + "\n" +
                "Ret: " + dish.getDisplayName() + "\n" +
                "Antal: " + quantity + "\n" +
                "Status: " + status;
    }
}
