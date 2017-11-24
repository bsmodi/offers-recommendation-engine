package com.offers.recommender;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import org.springframework.web.bind.annotation.*;

@RestController
public class OffersRecommendationController {

    private OffersRecommendationDAO offersRecommendationDAO;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getMCC")
    public ArrayList<Category> getMCC(@RequestParam(value="userId", defaultValue="10001") String userId) throws SQLException, ClassNotFoundException {
        offersRecommendationDAO = new OffersRecommendationDAO();
        return offersRecommendationDAO.getMCC(userId);
    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getOffers")
    public List<Coupon> getOffers(@RequestParam(value="userId", defaultValue="10001") String userId) throws SQLException, ClassNotFoundException, UnknownHostException {
        offersRecommendationDAO = new OffersRecommendationDAO();
        ObjectMapper mapper = new ObjectMapper();
        return offersRecommendationDAO.getOffers(userId);
        //return offersRecommendationDAO.getOffers(userId);
        //return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/setProfile")
    public void setProfile(@RequestBody Profile profile) {

    }


}
