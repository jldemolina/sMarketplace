package model;

import ejb.CartBean;

public class Customer extends User {
    private CartBean shoppingCart;
    private boolean premium;

    public Customer(String username, String email) {
        super(username, email);
    }

    public Customer(String username, String email, String ubication, String paymentMethod) {
        super(username, email, ubication, paymentMethod);
    }
    
    public Customer(CartBean shoppingCart, String username, String email) {
        super(username, email);
        this.shoppingCart = shoppingCart;
    }

    public void setShoppingCart(CartBean shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    public CartBean getShoppingCart() {
        return shoppingCart;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
     
}
