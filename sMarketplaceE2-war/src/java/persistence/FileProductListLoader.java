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

public class FileProductListLoader implements Loader {
    
    private final String file;
    @EJB
    private Catalogue catalogue;

    public FileProductListLoader(String file) {
        this.file = file;
        try {
            catalogue = (Catalogue) new InitialContext().lookup("java:app/sMarketplaceE2-ejb/CatalogueBean");
        } catch (NamingException ex) {
        }
    }

    @Override
    public void load() {
        Product script = null;
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
                while (true) {
                    String line = reader.readLine();
                    if (line == null)
                        break;
                    String[] productStringData = line.split("<>");
                    script = new Product(productStringData[0].trim(), 
                            productStringData[1].trim(),
                            new Customer(productStringData[2].trim(), productStringData[3].trim()),
                            Language.PHP,
                            Double.valueOf(productStringData[5].trim()),  productStringData[6].trim(),
                            new Image(productStringData[7].trim()));
                    catalogue.add(script);
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
