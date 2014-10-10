package model;

public class CartProduct {
    private Product product;
    private int quantify;

    public CartProduct(Product product, int quantify) {
        this.product = product;
        this.quantify = quantify;
    }

    public Product getProduct() {
        return product;
    }

    
    public int getQuantify() {
        return quantify;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }
    
    
    
}
