package com.lab.catalogue.web;

import com.lab.catalogue.domain.Catalogue;
import com.lab.catalogue.domain.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/v1/restaurant/{restId}/catalogue")
public class PublicCatalogueResource {
    @Autowired
    private CatalogueService service;

    @GetMapping("/current")
    public Catalogue currentCatalogue(@PathVariable String restId) {
        return service.current(restId);
    }
}
