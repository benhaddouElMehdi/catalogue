package com.lab.catalogue.domain;

import com.lab.catalogue.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;


    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant restaurant(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("catalogue not found for this restaurant : " + id));
    }

}
