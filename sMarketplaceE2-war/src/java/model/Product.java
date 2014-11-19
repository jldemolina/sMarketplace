package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable, Discountable {
    private final String id;
    private String name;
    private User author;
    private Language language;
    private double price;
    private String description;
    private Image image;
    private ArrayList<Discount> discounts;

    public Product(String id, String name, User author, Language language, double price, String description, Image image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.language = language;
        this.price = price;
        this.description = description;
        this.image = image;
        this.discounts = new ArrayList<Discount>();;
    }

    public String getId() {
        return id;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public double getPrice() {
        return Math.floor(price * 1e2) / 1e2;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public double getPriceWithDiscount() {
        double priceDiscounted = 0;
        for (Discount discount : discounts)
            priceDiscounted += discount.getPriceDiscounted(this);
        double finalPrice = Math.floor((price - priceDiscounted)* 1e2) / 1e2;
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
   
}
