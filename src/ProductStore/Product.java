package ProductStore;

public class Product {
    private String name;
    private double price;
    private double amount;
    private ProductType type;


    public Product(final String name, final double price, final double amount, final ProductType type) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }
    public void setPrice(final double price) {
        this.price = price;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
    public ProductType getType() {
        return type;
    }

    public void setType(final ProductType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductStore.Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
