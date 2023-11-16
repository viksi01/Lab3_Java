package exceptions;
import ProductStore.Product;

public class ProductIsNotInStockException extends Exception{
    public ProductIsNotInStockException(Product product) {
        super("Product" + product.getName() + "is not in stock!");
    }
}
