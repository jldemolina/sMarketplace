/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

public interface Product extends Serializable, Discountable {
    
    public String getId();
    public String getName();
    public void setName(String name);
    public User getAuthor();
    public void setAuthor(User author);
    public Language getLanguage();
    public void setLanguage(Language language);
    public double getPrice();
    public void setPrice(double price);
    public String getDescription();
    public void setDescription(String description);
    public Image getImage();
    public void setImage(Image image);
   
}
