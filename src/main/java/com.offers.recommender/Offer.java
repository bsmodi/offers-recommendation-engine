package com.offers.recommender;

public class Offer {
    String title;
    String description;
    String link;
    String mcc;

    public Offer(String title, String description, String link, String mcc) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.mcc = mcc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }
}
