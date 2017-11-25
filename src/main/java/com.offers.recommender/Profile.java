package com.offers.recommender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Profile {
    private String age, city, userId ;


    Profile(){}
    @JsonCreator
    public Profile(@JsonProperty("userId") String userId, @JsonProperty("age") String age,@JsonProperty("city") String city) {
        this.age = age;
        this.city = city;
        this.userId= userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
