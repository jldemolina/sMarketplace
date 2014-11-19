package model;

import java.util.ArrayList;

public interface Adherable {
    
    public double getPriceWithIncrements();
    public double getPriceIncremented();
    public ArrayList<PriceIncrement> getPriceIncrements();
    public void setPriceIncrements(ArrayList<PriceIncrement> increments);
    
}
