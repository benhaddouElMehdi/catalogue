package com.lab.catalogue;

import com.lab.catalogue.domain.CatalogueService;
import com.lab.catalogue.domain.RestaurantService;
import com.lab.catalogue.repository.CatalogueRepository;
import com.lab.catalogue.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Bean
    public RestaurantService restaurantService() {
        return new RestaurantService(restaurantRepository);
    }

    @Bean
    public CatalogueService catalogueService() {
        return new CatalogueService(catalogueRepository);
    }
}
