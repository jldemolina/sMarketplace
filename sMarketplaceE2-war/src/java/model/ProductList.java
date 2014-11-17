package model;

import java.util.ArrayList;

public class ProductList extends ArrayList<Product> {
    private static ProductList instance;
    
    private ProductList() {
    }
    
    public static ProductList getIntance() {
        if (instance == null) instance = new ProductList();
        return instance;
    }
    
    public Product searchByName(String name) {
        for (Product product : this)
            if (product.getName().equals(name))
                return product;
        return null;
    }
    
    public Product searchById(String id) {
        for (Product product : this)
            if (product.getId().equals(id))
                return product;                
        return null;
    }

    @Override
    public boolean add(Product product) {
        if (searchById(product.getId()) != null) return false;
        return super.add(product);
    }
    
}
