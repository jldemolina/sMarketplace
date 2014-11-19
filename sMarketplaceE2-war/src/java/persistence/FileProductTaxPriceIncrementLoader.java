package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import ejb.ShoppingCart;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TaxPriceIncrement;

public class FileProductTaxPriceIncrementLoader implements Loader {

    private final String file;
    private final ShoppingCart cart;
    private final String localization;

    public FileProductTaxPriceIncrementLoader(String file, ShoppingCart cart, String localization) {
        this.file = file;
        this.cart = cart;
        this.localization = localization;
    }

    @Override
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
            clear();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] productStringData = line.split("<>");
                if (localization.equalsIgnoreCase(productStringData[0].trim())) {
                    cart.getPriceIncrements().add((new TaxPriceIncrement(Double.valueOf(productStringData[1].trim()))));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProductTaxPriceIncrementLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileProductTaxPriceIncrementLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    private void clear() {
        for (int i = 0; i <  cart.getPriceIncrements().size(); i++)
            if (cart.getPriceIncrements().get(i) instanceof TaxPriceIncrement)
                cart.getPriceIncrements().remove(i);
    }
}
