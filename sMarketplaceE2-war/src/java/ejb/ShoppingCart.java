package ejb;

import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import model.Discountable;
import model.Product;

@Local
public interface ShoppingCart extends Discountable {

    public void initialize();
    public void addProduct(Product product); 
    public void removeProduct(Product product); 
    public Map<Product, Integer> getProducts();
    public int getTotalItems();
    public double getTotalPrice();
    public double getProductsPriceWithDiscount();
    public void clear();

}