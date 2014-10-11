/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Seruk
 */
public class ProductList extends ArrayList<Product> {
    private static ArrayList<Product> instance;
    
    private ProductList() {
    }
    
    public static ArrayList<Product> getIntance() {
        if (instance == null) instance = new ArrayList<Product>();
        return instance;
    }
    
}
