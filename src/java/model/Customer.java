package model;

public class Customer {
    private final ShoppingCart shoppingCart;
    private boolean premium;

    public Customer(ShoppingCart shoppingCart, boolean premium) {
        this.shoppingCart = shoppingCart;
        this.premium = premium;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
    
     
}
