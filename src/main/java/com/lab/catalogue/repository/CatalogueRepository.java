package com.lab.catalogue.repository;

import com.lab.catalogue.domain.Catalogue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogueRepository extends MongoRepository<Catalogue, String> {
}
