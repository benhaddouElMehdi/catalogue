package com.lab.catalogue.web;

import com.lab.catalogue.domain.Catalogue;
import com.lab.catalogue.domain.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/v1")
public class AdminCatalogueResource {

    @Autowired
    private CatalogueService service;

    @GetMapping("/catalogue/{catId}")
    public List<Catalogue> catalogue(@PathVariable String restId) {
        return service.catalogues(restId);
    }

    @PostMapping("/catalogue")
    public Catalogue addCatalogue(@RequestBody Catalogue catalogue) {
        return service.save(catalogue);
    }

    @DeleteMapping("/catalogue/{catId}")
    public void deleteCatalogue(@PathVariable String catId) {
        service.delete(catId);
    }

    @PutMapping("/catalogue")
    public void updateCatalogue(@RequestBody Catalogue catalogue) {
        service.update(catalogue);
    }

    @GetMapping("/{restId}/catalogue")
    public List<Catalogue> catalogues(@PathVariable String restId) {
        return service.catalogues(restId);
    }


}
