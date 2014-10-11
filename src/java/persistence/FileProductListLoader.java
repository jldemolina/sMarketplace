package persistence;

import model.Language;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.Customer;
import model.Image;
import model.Product;
import model.ProductList;

public class FileProductListLoader implements ProductListLoader {
    
    private final String file;

    public FileProductListLoader(String file) {
        this.file = file;
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
                    String[] productStringData = line.trim().split("|");
                    script = new Product(productStringData[0],
                            new Customer(productStringData[1], productStringData[2]),
                            Language.PHP, // TODO - EDIT
                            Double.valueOf(productStringData[4]), productStringData[5],
                            new Image(productStringData[6]));
                    ProductList.getIntance().add(script);
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
}
