package com.offers.recommender;

public class Profile {
    String age, city;

    public Profile(String age, String city) {
        this.age = age;
        this.city = city;
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
}
