package com.lab.catalogue.web;

import com.lab.catalogue.domain.Catalogue;
import com.lab.catalogue.domain.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PublicCatalogueResource {
    @Autowired
    private CatalogueService catalogueService;

    @GetMapping("/restaurant/{restId}/catalogue/current")
    public Catalogue current(@PathVariable String restId) {
        return catalogueService.current(restId)
                .orElseThrow(() -> new RuntimeException("no current found for restaurant : " + restId));
    }
}
