package com.lab.catalogue.domain;

import com.lab.catalogue.repository.CatalogueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    private CatalogueRepository catalogueRepository;

    private RestaurantService restaurantService;

    public CatalogueService(CatalogueRepository catalogueRepository, RestaurantService restaurantService) {
        this.catalogueRepository = catalogueRepository;
        this.restaurantService = restaurantService;
    }

    public List<Catalogue> catalogues(String restaurantId) {
        return catalogueRepository.findByRestaurantId(restaurantId);
    }

    public Catalogue save(Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }

    public Catalogue catalogue(String id) {
        return catalogueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no catalogue foud  " + id));
    }

    public void delete(String id) {
        catalogueRepository.deleteById(id);
    }

    public Catalogue current(String restId) {
        Restaurant restaurant = restaurantService.restaurant(restId);
        if (restaurant.hasCurrentCatalogue()) {
            return catalogue(restaurant.getCurrentCatalogue());
        }
        throw new RuntimeException("no current catalogue found for this restaurant : " + restId);
    }
}
