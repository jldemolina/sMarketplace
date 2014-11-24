package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import model.Product;

@Stateless
public class CatalogueBean implements Catalogue {

    private final ArrayList<Product> products;
    
    public CatalogueBean() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

   
    @Override
    public Product searchByName(String name) {
        for (Product product : products)
            if (product.getName().equals(name))
                return product;
        return null;
    }
    
    @Override
    public Product searchById(String id) {
        for (Product product : products)
            if (product.getId().equals(id))
                return product;                
        return null;
    }

    @Override
    public boolean add(Product product) {
        if (searchById(product.getId()) != null) return false;
        return products.add(product);
    }
    
}
