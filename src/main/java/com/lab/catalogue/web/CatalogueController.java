package com.lab.catalogue.web;

import com.lab.catalogue.domain.Catalogue;
import com.lab.catalogue.repository.CatalogueRepository;
import com.lab.catalogue.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant/{id}/catalogue")
public class CatalogueController {

    @Autowired
    private CatalogueRepository repository;


    @Autowired
    private RestaurantRepository restaurantRepository;


    @GetMapping
    public Catalogue find(@PathVariable String id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("catalogue not found for this restaurant : " + id))
                .getCatalogues()
                .stream()
                .filter(Catalogue::isCurrent)
                .findFirst().get();
    }
}
