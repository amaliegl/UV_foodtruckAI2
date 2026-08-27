package dk.zealand.domain;

public class Dish {
    private final String name;
    private final int price;

    public Dish(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDisplayName() {
        return name + " - " + price + " kr.";
    }
}
