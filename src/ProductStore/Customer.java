package ProductStore;

public class Customer {
    private String name;

    public Customer(final String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductStore.Customer{" +
                "name='" + name + '\'' +
                '}';
    }


}
