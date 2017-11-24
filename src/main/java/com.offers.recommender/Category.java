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

    public Category(int id, String merchantCategory, String offer, int price) {
        this.id = id;
        this.merchantCategory = merchantCategory;
        this.offer = offer;
        this.price = price;
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

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}