package com.lab.catalogue.web;

import com.lab.catalogue.domain.Restaurant;
import com.lab.catalogue.domain.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return service.save(restaurant);
    }

}
