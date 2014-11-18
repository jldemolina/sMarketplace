package model;

import java.util.HashMap;
import java.util.Map;

public class Invoice {
    private final User user;
    private Map<Product, Integer> products;

    public Invoice(User user, Map<Product, Integer> products) {
        this.user = user;
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public User getUser() {
        return user;
    }    
    
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    } 

}
