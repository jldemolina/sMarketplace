package model;

import ejb.ShoppingCart;

public class PercentageDiscount implements Discount {
    
    private double discount;

    public PercentageDiscount() {
        this.discount = 0;
    }

    public PercentageDiscount(double discount) {
        this.discount = discount;
    }
    
    @Override
    public double getPriceDiscounted(Object object) {
        if (object instanceof ShoppingCart) return getCartPriceDiscounted((ShoppingCart) object);
        else if (object instanceof Product) return getProductPriceDiscounted((Product) object);
        return 0;
    }
    
    private double getCartPriceDiscounted(ShoppingCart cart) {
       return cart.getProductsPriceWithDiscount() * (discount/ 100);
    }
    
    private double getProductPriceDiscounted(Product product) {
        return product.getPrice() * (discount / 100);
    }

    @Override
    public double getDiscount() {
        return discount;
    }
    
}
