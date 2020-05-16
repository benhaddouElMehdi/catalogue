package com.lab.catalogue.domain;

import com.lab.catalogue.repository.CatalogueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (catalogue.isCurrent()) {
            changeCurrentIfExists(catalogue);
        }
        return catalogueRepository.save(catalogue);
    }

    public Catalogue update(Catalogue catalogue) {
        catalogue(catalogue.getId());
        return save(catalogue);
    }

    private void changeCurrentIfExists(Catalogue catalogue) {
        Optional<Catalogue> current = current(catalogue.getRestaurantId());
        if (current.isPresent()) {
            changeCurrent(current);
        }
    }

    private void changeCurrent(Optional<Catalogue> current) {
        current.get().setCurrent(false);
        catalogueRepository.save(current.get());
    }

    public Catalogue catalogue(String id) {
        return catalogueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no catalogue foud : " + id));
    }

    public void delete(String id) {
        catalogueRepository.deleteById(id);
    }

    public Optional<Catalogue> current(String restId) {
        return catalogueRepository.findByRestaurantIdAndCurrent(restId, true);
    }
}
