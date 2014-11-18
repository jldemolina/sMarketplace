package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import model.Discount;
import model.Product;

@Stateless
public class CartBean implements ShoppingCart, Serializable {

    private HashMap<Product, Integer> cartProducts;
    private ArrayList<Discount> discounts;
    
    @Override
    @PostConstruct
    public void initialize() {
        cartProducts = new HashMap<>();
        discounts = new ArrayList<>();
    }
    
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
    public double getProductsPriceWithDiscount() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            total += entry.getKey().getPriceWithDiscount() * entry.getValue();
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

    @Override
    public double getPriceWithDiscount() {
        double priceDiscounted = 0;
        for (Discount discount : discounts)
            priceDiscounted += getTotalPrice() * (discount.getDiscount() / 100);
        double finalPrice = Math.floor((getProductsPriceWithDiscount() - priceDiscounted) * 1e2) / 1e2;
        return (finalPrice < 0)? 0 : finalPrice;
    }

    @Override
    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    @Override
    public void setDiscounts(ArrayList<Discount> discounts) {
        this.discounts = discounts;
    }
    
}
