package com.lab.catalogue.domain;

import com.lab.catalogue.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantServiceTest {

    private RestaurantRepository restaurantRepository = Mockito.mock(RestaurantRepository.class);

    private RestaurantService restaurantService = new RestaurantService(restaurantRepository);


    @Test
    public void find_restaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("restaurant");
        Mockito.when(restaurantRepository.findById(restaurant.getId())).thenReturn(Optional.of(restaurant));

        Restaurant restaurantFound = restaurantService.restaurant(restaurant.getId());

        assertEquals(restaurantFound.getId(), restaurant.getId());
        assertEquals(restaurantFound.getName(), restaurant.getName());
    }

    @Test
    public void save_restaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("restaurant");
        Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant saved = restaurantService.save(restaurant);

        assertEquals(saved.getId(), restaurant.getId());
        assertEquals(saved.getName(), restaurant.getName());
    }

    @Test
    public void update_restaurant() {
        Restaurant old = new Restaurant();
        old.setName("restaurant");
        Restaurant updated = new Restaurant();
        updated.setId(old.getId());
        updated.setName("restaurant updated");
        Mockito.when(restaurantRepository.findById(old.getId())).thenReturn(Optional.of(old));
        Mockito.when(restaurantRepository.save(updated)).thenReturn(updated);

        Restaurant updatedRestaurant = restaurantService.update(updated);

        assertEquals(old.getId(), updatedRestaurant.getId());
        assertEquals(updated.getName(), updatedRestaurant.getName());
    }
}