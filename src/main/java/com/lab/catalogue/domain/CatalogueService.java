package com.lab.catalogue.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    private RestaurantService restaurantService;

    public CatalogueService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Catalogue> catalogues(String restId) {
        return restaurantService.restaurant(restId).getCatalogues();
    }

    public void add(String restaurantId, Catalogue catalogue) {
        Restaurant restaurant = restaurantService.restaurant(restaurantId).addCatalogue(catalogue);
        restaurantService.save(restaurant);
    }

    public Catalogue catalogue(String restId, String catId) {
        return restaurantService.restaurant(restId)
                .catalogue(catId)
                .orElseThrow(() -> new RuntimeException("catalogue not foud found " + catId));
    }


    public Catalogue current(String restaurantId) {
        return restaurantService.restaurant(restaurantId).currentCatalogue()
                .orElseThrow(() -> new RuntimeException("current catalogue not foud found for restaurant " + restaurantId));

    }

    public void activate(String restId, String catId) {
        Restaurant restaurant = restaurantService.restaurant(restId).activateCatalogue(catId);
        restaurantService.save(restaurant);
    }

    public void desactivateCurrent(String restId) {
        Restaurant restaurant = restaurantService.restaurant(restId);
        restaurant.desactivateCurrentCatalogue();
        restaurantService.save(restaurant);
    }

    public Catalogue update(String restId, Catalogue catalogue) {
        Restaurant restaurant = restaurantService.restaurant(restId);
        restaurant.override(catalogue);
        restaurantService.save(restaurant);
        return catalogue;
    }

    public void delete(String restId, String catId) {
        Restaurant restaurant = restaurantService.restaurant(restId);
        restaurant.deleteCatalogue(catId);
        restaurantService.save(restaurant);
    }

}
