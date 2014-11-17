package ejb;

import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import model.Product;
import model.User;

@Remote
public interface ShoppingCart {

    public void initialize(User user);
    public void addProduct(Product product); 
    public void removeProduct(Product product); 
    public Map<Product, Integer> getProducts();
    public int getTotalItems();
    public double getTotalPrice();
    public void clear();

}