package com.offers.recommender;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class OffersRecommendationDAO {

    private static final String url = "jdbc:mysql://offer-recommendation-system.cko6hrw9syfz.us-west-2.rds.amazonaws.com:3306/offers";
    private static final String user = "user";
    private static final String password = "12345678";
    private static final String sqlClassName = "com.mysql.jdbc.Driver";

    public ArrayList<Offer> getOffers(String userId) throws SQLException, ClassNotFoundException {
        ArrayList<Offer> offers= new ArrayList<>();
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
            offers.add(new Offer(offersData.getInt("ID"), offersData.getString("MCC"), offersData.getString("MCC"), price));
            price = price - 100;
        }
        return offers;
    }
}
