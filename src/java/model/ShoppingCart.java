package model;

import java.util.ArrayList;
import java.util.Calendar;


public class ShoppingCart {
    private final String ID;
    private Calendar creationDate;
    private ArrayList<CartProduct> cartProducts;

    public ShoppingCart(String ID, Calendar creationDate, ArrayList<CartProduct> cartProducts) {
        this.ID = ID;
        this.creationDate = creationDate;
        this.cartProducts = cartProducts;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<CartProduct> getCartProducts() {
        return cartProducts;
    }
    
}
