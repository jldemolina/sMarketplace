/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Seruk
 */
public class Invoice {
    private final String name;
    private final String email;
    private final ArrayList<Product> products;

    public Invoice(String name, String email, ArrayList<Product> products) {
        this.name = name;
        this.email = email;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        String invoice = "Invoice for " + name + " with email " + email + "\n\n PRODUCT LIST";
        for (Product product : products) {
            invoice += "\t Product: " + product.getName() + " \t Price: " + product.getPrice() + "\n";
        }
        return invoice;
    }

    

}
