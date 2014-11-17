package model;

import java.io.Serializable;

public class Product implements Serializable {
    private final String id;
    private String name;
    private User author;
    private Language language;
    private double price;
    private String description;
    private Image image;

    public Product(String id, String name, User author, Language language, double price, String description, Image image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.language = language;
        this.price = price;
        this.description = description;
        this.image = image;
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
        return price;
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
   
}
