package model;

import ejb.ShoppingCart;

public class TaxPriceIncrement implements PriceIncrement {
    
    private final double increment;

    public TaxPriceIncrement() {
        this.increment = 0;
    }

    @Override
    public double getIncrement() {
        return increment;
    }

    @Override
    public double getPriceIncremented(Object object) {
        if (object instanceof ShoppingCart) {
            ShoppingCart cart = (ShoppingCart) object;
            return cart.getPriceWithDiscount() * (increment / 100);
        }
        return 0;
            
    }
    
}
