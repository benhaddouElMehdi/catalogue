package com.lab.catalogue.repository;

import com.lab.catalogue.domain.Catalogue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CatalogueRepository extends MongoRepository<Catalogue, String> {

    List<Catalogue> findByRestaurantId(String restaurantId);
}
