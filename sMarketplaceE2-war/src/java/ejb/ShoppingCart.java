package ejb;

import java.util.Map;
import javax.ejb.Remote;
import model.Product;

@Remote
public interface ShoppingCart {

    public void addProduct(Product product); 
    public void removeProduct(Product product); 
    public Map<Product, Integer> getProducts();
    public int getTotalItems();
    public double getTotalPrice();
    public void clear();

}