package com.offers.recommender;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OffersRecommendationController {

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getOffers")
    public ArrayList<Offer> getOffers(@RequestParam(value="userId", defaultValue="1001") String userId) {
        ArrayList<Offer> offers = new ArrayList<Offer>();
        Offer offer = new Offer(1L, "SUPER MARKET", "Big Bazar", 1200L);
        offers.add(offer);
        return offers;
    }
}
