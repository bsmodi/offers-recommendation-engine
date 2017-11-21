package com.offers.recommender;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OffersRecommendationController {

    @RequestMapping("/getOffers")
    public String getOffers() {
        /*String[] arguments;
        arguments = new String[2];
        arguments[0] = "src/trainingData.csv";
        arguments[1] = "5";*/
       // RecommendationEngine.getOffers(arguments);
        return "Yayy! Got offers";
    }
}
