package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import model.Discount;
import model.PriceIncrement;
import model.Product;

@Stateless
public class CartBean implements ShoppingCart, Serializable {

    private HashMap<Product, Integer> cartProducts;
    private ArrayList<Discount> discounts;
    private ArrayList<PriceIncrement> increments;
    
    @Override
    @PostConstruct
    public void initialize() {
        cartProducts = new HashMap<>();
        discounts = new ArrayList<>();
        increments = new ArrayList<>();
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
        for (Discount discount : discounts) {
            priceDiscounted += discount.getPriceDiscounted(this);
        }
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

    @Override
    public double getPriceWithIncrements() {
        double priceIncremented = 0;
        for (PriceIncrement increment : increments) {
            priceIncremented += increment.getPriceIncremented(this);
        }
        return Math.floor((getProductsPriceWithDiscount() + priceIncremented) * 1e2) / 1e2;
    }

    @Override
    public ArrayList<PriceIncrement> getPriceIncrements() {
        return increments;
    }

    @Override
    public void setPriceIncrements(ArrayList<PriceIncrement> increments) {
        this.increments = increments;
    }

    @Override
    public double getPriceDiscounted() {
        return getTotalPrice() - getProductsPriceWithDiscount();
    }

    @Override
    public double getPriceIncremented() {
        double priceIncremented = 0;
        
        for (PriceIncrement increment : increments) {
            priceIncremented += increment.getPriceIncremented(this);
        }
        return Math.floor((priceIncremented) * 1e2) / 1e2;
    }
    
}
