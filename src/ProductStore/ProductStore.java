package ProductStore;

import exceptions.ProductIsNotInStockException;

import java.util.ArrayList;
import java.util.List;

public class ProductStore {
    private List<Product> products = new ArrayList<>();

    public boolean isInStock(final Product product, final int amount) {
        return products.stream()
                .anyMatch(p -> p.getName().equals(product.getName()) && p.getAmount() >= amount);
    }

    public void addProduct(final Product product) {
        products.add(product);
    }

    public Product sellProduct(final Product product, final int amount) throws ProductIsNotInStockException {
        if (isInStock(product, amount)) {
            products.stream()
                    .filter(p -> p.getName().equals(product.getName()))
                    .forEach(p -> p.setAmount(p.getAmount() - amount));
            return new Product(product.getName(), product.getPrice(), amount, product.getType());


        } else {
            throw new ProductIsNotInStockException(product);
        }

    }


    public void editProduct(final Product product, final double price, final double amount) {
        products.stream()
                .filter(p -> p.getName().equals(product.getName()))
                .forEach(p -> {
                    p.setPrice(price);
                    p.setAmount(amount);
                });
    }

    public void printProducts() {
        products.forEach(p -> System.out.println(p.getName() + "," + p.getPrice() + "," + p.getAmount()));
    }


    public void filterByPrice (final double price) {
        products.stream()
            .filter(p -> p.getPrice() < price)
            .forEach(p -> System.out.println(p.getName() + "," + p.getPrice() + "," + p.getAmount()));
    }

    public void sortByPrice () {
        products.stream()
            .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
            .forEach(p -> System.out.println(p.getName() + "," + p.getPrice() + "," + p.getAmount()));
    }

    public void averagePrice () {
        final double average = products.stream()
            .mapToDouble(p -> p.getPrice())
            .average()
            .getAsDouble();
        System.out.println(average);
    }

}
