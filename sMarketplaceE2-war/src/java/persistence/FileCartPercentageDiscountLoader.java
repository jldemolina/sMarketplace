package persistence;


import ejb.ShoppingCart;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.PercentageDiscount;
import persistence.Loader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Seruk
 */
public class FileCartPercentageDiscountLoader implements Loader {

    private final String file;
    @EJB
    private ShoppingCart cart;

    public FileCartPercentageDiscountLoader(String file) {
        this.file = file;
        try {
            cart = (ShoppingCart) new InitialContext().lookup("java:app/sMarketplaceE2-war/CartBean");
        } catch (NamingException ex) {
        }
    }

    @Override
    public void load() {
        cart.getDiscounts().clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] productStringData = line.split("<>");
                if (productStringData[1].trim().equals("active")) {
                    cart.getDiscounts().add(new PercentageDiscount(cart, Double.valueOf(productStringData[2].trim())));
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
