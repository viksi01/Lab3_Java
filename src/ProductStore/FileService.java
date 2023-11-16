package ProductStore;

import exceptions.LoadToFileException;
import exceptions.LoadFromFileException;
import exceptions.ReceiptException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {
    public static List<Product> loadProductsFromFile(final String filePath) throws LoadFromFileException {
        try (final BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(FileService::parseProduct)
                    .filter(product -> product != null)
                    .collect(Collectors.toList());
        } catch (final IOException | NumberFormatException e) {
            throw new LoadFromFileException();
        }
    }

    public static void saveProductsToFile(final List<Product> products, final String filePath) throws LoadToFileException {
        try (final FileWriter writer = new FileWriter(filePath)) {
            products.forEach(product -> {
                try {
                    writer.write(product.getName() + "," + product.getPrice() + "," + product.getAmount() + "\n");
                } catch (final IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (final IOException e) {
            throw new LoadToFileException();
        }
    }


    public static void generateReceipt(final Receipt receipt, final String filePath) throws ReceiptException {
        try (final FileWriter writer = new FileWriter(filePath)) {
            writer.write("Customer: " + receipt.getCustomer().getName() + "\n");

            receipt.getProducts().forEach(p -> {
                try {
                    writer.write(p.getName() + "," + p.getPrice() + "," + p.getAmount() + "\n");
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            });

            writer.write("Total price: " + receipt.getTotalPrice() + "\n");
            writer.write("Comment: ");
            receipt.receiptComment(writer);
        } catch (final IOException e) {
            throw new ReceiptException();
        }
    }




    private static Product parseProduct(final String line) {
        final int lenghtNumber = 4;
        try {
            final String[] parts = line.split(",");
            if (parts.length == lenghtNumber) {
                final String name = parts[0].trim();
                final double price = Double.parseDouble(parts[1].trim());
                final int amount = Integer.parseInt(parts[2].trim());
                final ProductType type = ProductType.valueOf(parts[3].trim());
                return new Product(name, price, amount, type);
            }
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
