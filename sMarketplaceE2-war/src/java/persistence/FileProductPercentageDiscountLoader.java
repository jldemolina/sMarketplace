package persistence;

import ejb.Catalogue;
import model.Language;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.Customer;
import model.Image;
import model.Product;
import ejb.CatalogueBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Discount;
import model.PercentageDiscount;

public class FileProductPercentageDiscountLoader implements Loader {
    
    private final String file;
    @EJB
    private Catalogue catalogue;

    public FileProductPercentageDiscountLoader(String file) {
        this.file = file;
        try {
            catalogue = (Catalogue) new InitialContext().lookup("java:app/sMarketplaceE2-war/CatalogueBean");
        } catch (NamingException ex) {
        }
    }

    @Override
    public void load() {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
                while (true) {
                    String line = reader.readLine();
                    if (line == null)
                        break;
                    String[] productStringData = line.split("<>");
                    Product product = catalogue.searchByName(productStringData[0]);
                    if (product != null) {
                        product.getDiscounts().add(new PercentageDiscount(product, Double.valueOf(productStringData[1].trim())));
                    }
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}

