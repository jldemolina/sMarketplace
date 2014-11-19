package model;

import java.util.ArrayList;

public interface Adherable {
    
    public double getPriceWithIncrements();
    public ArrayList<PriceIncrement> getPriceIncrements();
    public void setPriceIncrementss(ArrayList<PriceIncrement> increments);
    
}
