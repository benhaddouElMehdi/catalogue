package com.lab.catalogue.repository;

import com.lab.catalogue.domain.Catalogue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogueRepository extends MongoRepository<Catalogue, String> {

    List<Catalogue> findByRestaurantId(String restaurantId);

    Optional<Catalogue> findByRestaurantIdAndCurrent(String restaurantId, boolean isCurrent);
}
