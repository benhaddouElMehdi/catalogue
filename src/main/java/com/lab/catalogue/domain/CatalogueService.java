package com.lab.catalogue.domain;

import com.lab.catalogue.repository.CatalogueRepository;

import java.util.List;
import java.util.Optional;

public class CatalogueService {

    private CatalogueRepository catalogueRepository;

    public CatalogueService(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public List<Catalogue> cataloguesByRestaurantId(String restaurantId) {
        return catalogueRepository.findByRestaurantId(restaurantId);
    }

    public Catalogue save(Catalogue catalogue) {
        if (catalogue.isCurrent()) {
            deactivateCurrentIfExists(catalogue);
        }
        return catalogueRepository.save(catalogue);
    }

    public Catalogue update(Catalogue catalogue) {
        catalogue(catalogue.getId());
        return save(catalogue);
    }

    public Catalogue catalogue(String id) {
        return catalogueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no catalogue found : " + id));
    }

    public void delete(String id) {
        catalogue(id);
        catalogueRepository.deleteById(id);
    }

    public Optional<Catalogue> current(String restId) {
        return catalogueRepository.findByRestaurantIdAndCurrent(restId, true);
    }

    private void deactivateCurrentIfExists(Catalogue catalogue) {
        Optional<Catalogue> current = current(catalogue.getRestaurantId());
        current.ifPresent(this::deactivateCurrent);
    }

    private void deactivateCurrent(Catalogue current) {
        current.setCurrent(false);
        catalogueRepository.save(current);
    }
}
