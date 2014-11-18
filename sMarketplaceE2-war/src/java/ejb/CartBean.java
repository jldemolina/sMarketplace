package ejb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import model.Product;
import model.User;

@Stateless
public class CartBean implements ShoppingCart, Serializable {

    private HashMap<Product, Integer> cartProducts = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            if (product.getId().equals(entry.getKey().getId())) {
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        cartProducts.put(product, 1);
    }

    @Override
    public void removeProduct(Product product) {
        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            if (product.getId().equals(entry.getKey().getId())) {
                if (entry.getValue() > 1) {
                    entry.setValue(entry.getValue() - 1);
                    break;
                } else {
                    cartProducts.remove(entry.getKey());
                    break;
                }
            }
        }
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
        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    @Override
    public int getTotalItems() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            total += 1 * entry.getValue();
        }
        return total;
    }

}
