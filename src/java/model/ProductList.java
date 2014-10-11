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
    
    public Product search(String name) {
        for (Product product : this)
            if (product.getName().equals(name))
                return product;
        return null;
    }

    @Override
    public boolean add(Product product) {
        if (search(product.getName()) != null) return false;
        return super.add(product);
    }
    
}
