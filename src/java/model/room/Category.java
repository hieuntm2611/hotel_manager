/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.room;

import java.util.ArrayList;

/**
 *
 * @author hieu2
 */
public class Category {
    private int id;
    private String name;
    private double price;
    private ArrayList<Utility> utilities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Utility> getUtilities() {
        return utilities;
    }

    public void setUtilities(ArrayList<Utility> utilities) {
        this.utilities = utilities;
    }
    
    public boolean checkUtiliti(int utilitiId){
        for (Utility utility : utilities) {
            if(utility.getId()==utilitiId){
                return true;
            }
        }
        return false;
    }
    
}
