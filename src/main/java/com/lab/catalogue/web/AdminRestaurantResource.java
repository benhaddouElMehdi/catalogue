package com.lab.catalogue.web;

import com.lab.catalogue.domain.Catalogue;
import com.lab.catalogue.domain.CatalogueService;
import com.lab.catalogue.domain.Restaurant;
import com.lab.catalogue.domain.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/v1/restaurant")
public class AdminRestaurantResource {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CatalogueService catalogueService;

    @GetMapping("/version")
    public String version() {
        return "1.0-SNAPSHOT";
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

    @PutMapping
    public Restaurant update(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

}
