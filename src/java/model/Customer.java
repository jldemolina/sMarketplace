package model;

public class Customer extends User {
    private CartBeans shoppingCart;
    private boolean premium;

    public Customer(String username, String email) {
        super(username, email);
    }

    public Customer(CartBeans shoppingCart, String username, String email) {
        super(username, email);
        this.shoppingCart = shoppingCart;
    }

    public void setShoppingCart(CartBeans shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    public CartBeans getShoppingCart() {
        return shoppingCart;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
     
}
