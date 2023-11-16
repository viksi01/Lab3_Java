package ProductStore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Receipt {
    private Customer customer;
    private List<Product> products;

    public Receipt(final Customer customer, final List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
    public void setProducts(final List<Product> products) {
        this.products = products;
    }



    public void printReceipt() {
        System.out.println("Customer: " + customer.getName());
        products.stream()
                .forEach(p -> System.out.println(p.getName() + "," + p.getPrice() + "," + p.getAmount()));
        System.out.println("Total price: " + getTotalPrice());
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(p -> p.getPrice() * p.getAmount())
                .sum();
    }

    public void receiptComment(final FileWriter writer) {
        products.stream()
                .filter(p -> p.getType().equals(ProductType.MEAT) || p.getType().equals(ProductType.FISH))
                .forEach(p -> {
                    try {
                        writer.write("Don't forget to store " + p.getName() + " in the fridge!\n");
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                });

        products.stream()
                .filter(p -> p.getType().equals(ProductType.VEGETABLE) || p.getType().equals(ProductType.FRUIT))
                .forEach(p -> {
                    try {
                        writer.write("Package added!\n");
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                });
    }

}
