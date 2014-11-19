package ejb;

import java.util.ArrayList;
import javax.ejb.Local;
import model.Product;

@Local
public interface Catalogue {

    public Product searchByName(String name);
    public Product searchById(String id);
    public boolean add(Product product);
    public ArrayList<Product> getProducts();
    
}
