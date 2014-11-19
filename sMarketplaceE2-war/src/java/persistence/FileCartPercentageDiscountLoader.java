package persistence;

import ejb.ShoppingCart;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.PercentageDiscount;

public class FileCartPercentageDiscountLoader implements Loader {

    private final String file;
    private final ShoppingCart cart;

    public FileCartPercentageDiscountLoader(String file, ShoppingCart cart) {
        this.file = file;
        this.cart = cart;
    }

    @Override
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
            cart.getDiscounts().clear();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] productStringData = line.split("<>");
                if (productStringData[1].trim().equals("active")) {
                    cart.getDiscounts().add(new PercentageDiscount(Double.valueOf(productStringData[2].trim())));
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
