package model;

import java.util.HashMap;
import java.util.Map;

public class CartBeans implements ShoppingCart {
    private HashMap<Product, Integer> cartProducts;

    @Override
    public void initialize(User user) {
        cartProducts = new HashMap<>();
    }
    
    @Override
    public void addProduct(Product product) {
        if (cartProducts.containsKey(product)) 
            cartProducts.put(product, cartProducts.get(product) + 1);
        else 
            cartProducts.put(product, 1);   
    }

    @Override
    public void removeProduct(Product product) {
        if (cartProducts.get(product) > 1)
            cartProducts.put(product, cartProducts.get(product) - 1);
        else
           cartProducts.remove(product);  
    }

    @Override
    public HashMap<Product, Integer> getProducts() {
        return new HashMap<>(cartProducts);
    }

    @Override
    public void clear() {
        cartProducts.clear();
    }
    
    public double getTotalPrice() {
        double total = 0;
        for(Map.Entry<Product, Integer> entry : cartProducts.entrySet())
            total += entry.getKey().getPrice() * entry.getValue();
        return total;
    }
    
    public int getTotalItems() {
        int total = 0;
        for(Map.Entry<Product, Integer> entry : cartProducts.entrySet())
            total += 1 * entry.getValue();
        return total;
    }
    
}