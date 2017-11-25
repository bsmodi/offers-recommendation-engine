package com.offers.recommender;
import java.io.Serializable;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Category implements Serializable{

    int id;
    String merchantCategory;
    String offer;
    int price;

    public Category(int id, String merchantCategory) {
        this.id = id;
        this.merchantCategory = merchantCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(String merchantCategory) {
        this.merchantCategory = merchantCategory;
    }
}