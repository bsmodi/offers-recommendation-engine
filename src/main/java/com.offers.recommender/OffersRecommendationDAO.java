package com.offers.recommender;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OffersRecommendationDAO {

    private static final String url = "jdbc:mysql://offer-recommendation-system.cko6hrw9syfz.us-west-2.rds.amazonaws.com:3306/offers";
    private static final String user = "user";
    private static final String password = "12345678";
    private static final String sqlClassName = "com.mysql.jdbc.Driver";

    public ArrayList<Category> getMCC(String userId) throws SQLException, ClassNotFoundException {
        ArrayList<Category> categories = new ArrayList<>();
        Connection connection = null;
        Class.forName(sqlClassName);
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        connection = DriverManager.getConnection(url, properties);
        String getOffersSqlStmt = "select * from recommendations where UserID=(?) order by Rating desc";
        PreparedStatement preparedStatement = connection.prepareStatement(getOffersSqlStmt);
        int id = Integer.parseInt(userId);
        preparedStatement.setInt(1,id);
        ResultSet offersData = preparedStatement.executeQuery();
        int price = 2000;
        while (offersData.next()) {
            categories.add(new Category(offersData.getInt("ID"), offersData.getString("MCC"), offersData.getString("MCC"), price));
            price = price - 100;
        }
        return categories;
    }

    public ArrayList<Offer> getOffers(String userId) throws SQLException, ClassNotFoundException, UnknownHostException {
        ArrayList<Offer> offers = new ArrayList<>();
        ArrayList<Category> categories = this.getMCC(userId);
        ArrayList<String> mccList = new ArrayList<>();
        for (Category category: categories) {
            mccList.add(category.getMerchantCategory());
        }

        MongoClientURI uri = new MongoClientURI("mongodb://Happy123:Happy123@offerrecommendationengine-shard-00-00-utons.mongodb.net:27017,offerrecommendationengine-shard-00-01-utons.mongodb.net:27017,offerrecommendationengine-shard-00-02-utons.mongodb.net:27017/test?ssl=true&replicaSet=OfferRecommendationEngine-shard-0&authSource=admin");
        MongoClient mongoClient = new MongoClient(uri);
        DB database = mongoClient.getDB("recommendedOffers");
        DBCollection coupons = database.getCollection("coupons");
        BasicDBObject searchQuery = new BasicDBObject();
        for (String category: mccList) {
            searchQuery.put("couponId.MCC", category);
            DBCursor cursor = coupons.find(searchQuery);
            while (cursor.hasNext()) {
                Offer offer = (Offer) cursor.next();
                offers.add(offer);

            }
        }

        /*Offer offer = new Offer("Extra 13% OFF on Minimum purchase of Rs 500/- (Not applicable on electronic products)", "Astonishing offer from ShopClues. Click on Get Code to unlock coupon and visit merchant. Coupon expires on 24 May 2018. Enjoy Rs. 500 on All Categories Except Electronics - Min Purchase 500 listed on the listing page. Use this Coupon and get discount of Rs. 500 from ShopClues. This Coupon is usable to all users. Electronics is not valid for this Coupon.", "http://www.shopclues.com/", "HOLIDAY INNS");
        offers.add(offer);*/
        return offers;
    }
}
