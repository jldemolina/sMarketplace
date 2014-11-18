package model;

import ejb.ShoppingCart;

public class Invoice {
    private final User user;
    private ShoppingCart cart;

    public Invoice(User user, ShoppingCart cart) {
        this.user = user;
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public User getUser() {
        return user;
    }    
}
