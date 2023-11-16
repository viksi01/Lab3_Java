package ProductStore;

import exceptions.LoadFromFileException;
import exceptions.ProductIsNotInStockException;
import exceptions.ReceiptException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws ProductIsNotInStockException, LoadFromFileException, ReceiptException {
        final String productsFilePath = "C:\\LPNU\\Java\\Lab3_Java\\files\\Products.txt";

        final ProductStore productStore = new ProductStore();

        final List<Product> products = FileService.loadProductsFromFile(productsFilePath);
        products.forEach(product -> productStore.addProduct(product));

        final Product product1 = new Product("Banana", 30.0, 6, ProductType.FRUIT);
        final Product product2 = new Product("Pork", 50.0, 10, ProductType.MEAT);
        final Product product3 = products.get(0);
        final Product product4 = products.get(1);

        productStore.addProduct(product1);
        productStore.addProduct(product2);

        System.out.println("All products:");
        productStore.printProducts();

        System.out.println("=====================");
        System.out.println("Products with price less than 50:");
        productStore.filterByPrice(50);

        System.out.println("=====================");
        System.out.println("Sorted products by price:");
        productStore.sortByPrice();

        System.out.println("=====================");
        System.out.println("Average price:");
        productStore.averagePrice();

        System.out.println("=====================");

        final Customer customer = new Customer("Vika");
        final List<Product> customerProducts = new ArrayList<>();

        customerProducts.add(productStore.sellProduct(product1, 1));
        customerProducts.add(productStore.sellProduct(product2, 2));
        customerProducts.add(productStore.sellProduct(product3, 1));


        final Receipt receipt = new Receipt(customer, customerProducts);
        receipt.printReceipt();
        final String receiptFilePath = "C:\\LPNU\\Java\\Lab3_Java\\files\\Receipt.txt";
        FileService.generateReceipt(receipt, receiptFilePath);

        System.out.println("=====================");
        System.out.println("Updated products:");
        productStore.printProducts();

    }
}
