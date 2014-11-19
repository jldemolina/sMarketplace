package model;

import java.util.ArrayList;

public interface Discountable {
    
    public double getPriceWithDiscount();
    public double getPriceDiscounted();
    public ArrayList<Discount> getDiscounts();
    public void setDiscounts(ArrayList<Discount> discounts);
    
}
