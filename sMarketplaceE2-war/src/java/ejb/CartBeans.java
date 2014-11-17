package ejb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import model.Product;
import model.User;

@Stateless
public class CartBeans implements ShoppingCart, Serializable {
    private HashMap<Product, Integer> cartProducts = new HashMap<>();

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
        return cartProducts;
    }

    @Override
    public void clear() {
        cartProducts.clear();
    }
    
    @Override
    public double getTotalPrice() {
        double total = 0;
        for(Map.Entry<Product, Integer> entry : cartProducts.entrySet())
            total += entry.getKey().getPrice() * entry.getValue();
        return total;
    }
    
    @Override
    public int getTotalItems() {
        int total = 0;
        for(Map.Entry<Product, Integer> entry : cartProducts.entrySet())
            total += 1 * entry.getValue();
        return total;
    }
    
}