package persistence;

import objects.Product;
import ejb.Catalogue;
import model.Language;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.Customer;
import model.Image;
import ejb.CatalogueBean;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FileProductListLoader implements Loader {

    private final String file;
    
    private final Catalogue catalogue;
    
    private final int firstLine;
    private final int lastLine;

    public FileProductListLoader(String file, int firstLine, int lastLine, Catalogue catalogue) {
        this.file = file;
        this.firstLine = firstLine;
        this.lastLine = lastLine;
        this.catalogue = catalogue;
    }

    @Override
    public void load() {
        if (firstLine < 0 || lastLine < 0) return;
        catalogue.getProducts().clear();
        ArrayList<Product> products = readLines();
        for (int i = firstLine; i < lastLine; i++) {
            if (i >= products.size()) return;
            catalogue.add(products.get(i));
        }
    }

    private ArrayList<Product> readLines() {
        Product script = null;
        ArrayList<Product> products = new ArrayList();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    String[] productStringData = line.split("<>");
                    script = new Product(productStringData[0].trim(),
                            productStringData[1].trim(),
                            new Customer(productStringData[2].trim(), productStringData[3].trim()),
                            Language.PHP,
                            Double.valueOf(productStringData[5].trim()), productStringData[6].trim(),
                            new Image(productStringData[7].trim()));
                    products.add(script);
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return products;
    }
}
