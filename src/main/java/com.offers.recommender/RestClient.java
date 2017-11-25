package com.offers.recommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anuhyacheruvu on 25/11/17.
 */
@Component
public class RestClient  {


    RestTemplate restTemplate = new RestTemplate();

    private static String URL = "http://ec2-54-88-51-233.compute-1.amazonaws.com:9095/rest/getOffer";

    public Coupon predictions(Profile profile, String category)
    {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", String.valueOf(MediaType.APPLICATION_JSON));
        headers.add("Basic", "Y2xpZW50MTpiY3VzIzEyMzQ=");
        //restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("client1", "bcus#1234"));

        /*Map<String,String > request = new HashMap<>();
        request.put("age", profile.getAge());
        request.put("city", profile.getCity());
        request.put("eventCategory", category);*/
        HttpEntity<GetOffersData> entity = new HttpEntity<>(new GetOffersData(profile.getAge(), profile.getCity(), category), headers);

        ResponseEntity<Coupon> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, Coupon.class);

        //ResponseEntity<Coupon> responseEntity = restTemplate.postForEntity(URL,request, Coupon.class);

        return responseEntity.getBody();
    }
}
