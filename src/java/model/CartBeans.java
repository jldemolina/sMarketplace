package model;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateful;

@Stateful
public class CartBeans implements ShoppingCart {
    private User user;
    private Map<Product, Integer> cartProducts;

    @Override
    public void initialize(User user) {
        cartProducts = new HashMap<>();
        this.user = user;
    }

    @Override
    public void addProduct(Product product) {
        if (getQuantify(product) == null) cartProducts.put(product, 0);
        else cartProducts.put(product, cartProducts.get(product) + 1);
        
    }

    @Override
    public void removeProduct(Product product) {
        if (getQuantify(product) == 0) cartProducts.remove(product);
        else cartProducts.put(product, cartProducts.get(product) - 1);
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return new HashMap<>(cartProducts);
    }

    @Override
    public void clear() {
        cartProducts.clear();
    }
    
    private Integer getQuantify(Product product) {
        for(Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            if (entry.getKey().getName().equals(product.getName()));
                return entry.getValue();
        }
        return null;
    }
    
}