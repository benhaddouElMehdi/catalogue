package com.lab.catalogue.domain;

import com.lab.catalogue.repository.RestaurantRepository;

public class RestaurantService {

    private RestaurantRepository restaurantRepository;


    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        restaurant(restaurant.getId());
        return save(restaurant);
    }

    public Restaurant restaurant(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("restaurant not found  : " + id));
    }
}
