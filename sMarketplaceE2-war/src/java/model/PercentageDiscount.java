package model;


import model.Discount;
import ejb.ShoppingCart;
import model.Product;

public class PercentageDiscount implements Discount {
    
    private Object object;
    private double discount;

    public PercentageDiscount() {
    }

    public PercentageDiscount(Object object, double discount) {
        this.object = object;
        this.discount = discount;
    }
    
    @Override
    public double getPriceDiscounted() {
        if (object instanceof ShoppingCart) return getCartPriceDiscounted((ShoppingCart) object);
        else if (object instanceof Product) return getProductPriceDiscounted((Product) object);
        return 0;
    }
    
    private double getCartPriceDiscounted(ShoppingCart cart) {
        double pricew = cart.getProductsPriceWithDiscount();
        double pricez = cart.getProductsPriceWithDiscount() * (discount / 100);
       return cart.getProductsPriceWithDiscount() * (10 / 100);
    }
    
    private double getProductPriceDiscounted(Product product) {
        return product.getPrice() * (discount / 100);
    }

    @Override
    public double getDiscount() {
        return discount;
    }
    
}
