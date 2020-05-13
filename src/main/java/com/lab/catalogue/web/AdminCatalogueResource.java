package com.lab.catalogue.web;

import com.lab.catalogue.domain.Catalogue;
import com.lab.catalogue.domain.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/v1/restaurant/{restId}/catalogue")
public class AdminCatalogueResource {

    @Autowired
    private CatalogueService service;


    @GetMapping
    public List<Catalogue> catalogues(@PathVariable String restId) {
        return service.catalogues(restId);
    }

    @PostMapping
    public void addCatalogue(@PathVariable String restId, @RequestBody Catalogue catalogue) {
        service.add(restId, catalogue);
    }

    @DeleteMapping("/{catId}")
    public void deleteCatalogue(@PathVariable String restId, @PathVariable String catId) {
        service.delete(restId, catId);
    }

    @PutMapping
    public void updateCatalogue(@PathVariable String restId, @RequestBody Catalogue catalogue) {
        service.update(restId, catalogue);
    }

    @GetMapping("{catId}/activate")
    public void activateCatalogue(@PathVariable String restId, @PathVariable String catId) {
        service.activate(restId, catId);
    }

    @GetMapping("/current/desactivate")
    public void desactivateCatalogue(@PathVariable String restId) {
        service.desactivateCurrent(restId);
    }
}
