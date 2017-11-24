package com.offers.recommender;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OffersRecommendationController {

    private OffersRecommendationDAO offersRecommendationDAO;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getMCC")
    public ArrayList<Category> getMCC(@RequestParam(value="userId", defaultValue="10001") String userId) throws SQLException, ClassNotFoundException {
        offersRecommendationDAO = new OffersRecommendationDAO();
        return offersRecommendationDAO.getMCC(userId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getOffers")
    public ArrayList<Offer> getOffers(@RequestParam(value="userId", defaultValue="10001") String userId) throws SQLException, ClassNotFoundException, UnknownHostException {
        offersRecommendationDAO = new OffersRecommendationDAO();
        return offersRecommendationDAO.getOffers(userId);
    }


}
