package com.offers.recommender;

import java.io.Serializable;

/**
 * Created by anuhyacheruvu on 25/11/17.
 */
public class GetOffersData implements Serializable
{

    public String age;
    public String city;
    public String eventCategory;

    public GetOffersData(String age, String city, String eventCategory) {
        this.age = age;
        this.city = city;
        this.eventCategory = eventCategory;
    }
}
