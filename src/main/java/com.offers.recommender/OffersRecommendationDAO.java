package com.offers.recommender;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONException;
import org.json.JSONObject;

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
        preparedStatement.setInt(1, id);
        ResultSet offersData = preparedStatement.executeQuery();
        while (offersData.next()) {
            categories.add(new Category(offersData.getInt("ID"), offersData.getString("MCC")));
        }
        return categories;
    }

    public List<Coupon> getOffers(String userId) throws SQLException, ClassNotFoundException, UnknownHostException {
        List<Category> categories = this.getMCC(userId);
        List<String> mccList = new ArrayList<>();
        List<Coupon> offers = new ArrayList<>();
        for (Category category : categories) {
            mccList.add(category.getMerchantCategory());
        }

        MongoClientURI uri = new MongoClientURI("mongodb://Happy123:Happy123@offerrecommendationengine-shard-00-00-utons.mongodb.net:27017,offerrecommendationengine-shard-00-01-utons.mongodb.net:27017,offerrecommendationengine-shard-00-02-utons.mongodb.net:27017/test?ssl=true&replicaSet=OfferRecommendationEngine-shard-0&authSource=admin");
        MongoClient mongoClient = new MongoClient(uri);
        DB database = mongoClient.getDB("recommendedOffers");
        DBCollection coupons = database.getCollection("coupons");
        BasicDBObject searchQuery = new BasicDBObject();

        for (String category : mccList) {
            searchQuery.put("couponId.MCC", category);
            BasicDBObject dbObject = (BasicDBObject) coupons.findOne(searchQuery);
            if(dbObject!=null) {
                    Coupon offer = new Coupon();
                    BasicDBObject couponDetails = (BasicDBObject) dbObject.get("couponId");
                    offer.setCategory((String) couponDetails.get("MCC"));
                    offer.setDate((String) couponDetails.get("VALIDITY_DATE"));
                    offer.setDesc((String) couponDetails.get("DESCRIPTION"));
                    offer.setTitle((String) couponDetails.get("TITLE"));
                    offer.setLink((String) couponDetails.get("LINK"));
                    offers.add(offer);
            }
        }
        return offers;
    }
}
