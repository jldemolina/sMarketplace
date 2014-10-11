package model;

import java.util.Map;
import javax.ejb.Remote;

@Remote
public interface ShoppingCart {

    public void initialize(User user);
    public void addProduct(Product product); 
    public void removeProduct(Product product); 
    public Map<Product, Integer> getProducts();
    public void clear();

}