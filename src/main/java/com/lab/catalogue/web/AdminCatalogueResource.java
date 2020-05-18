package com.lab.catalogue.web;

import com.lab.catalogue.domain.Catalogue;
import com.lab.catalogue.domain.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminCatalogueResource {

    @Autowired
    private CatalogueService service;

    @GetMapping("/catalogue/{id}")
    public Catalogue catalogue(@PathVariable String id) {
        return service.catalogue(id);
    }

    @PostMapping("/catalogue")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Catalogue addCatalogue(@RequestBody Catalogue catalogue) {
        return service.save(catalogue);
    }

    @DeleteMapping("/catalogue/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCatalogue(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping("/catalogue")
    @ResponseStatus(code = HttpStatus.OK)
    public Catalogue updateCatalogue(@RequestBody Catalogue catalogue) {
        return service.update(catalogue);
    }

    @GetMapping("/restaurant/{restId}/catalogue")
    public List<Catalogue> cataloguesByRestaurant(@PathVariable String restId) {
        return service.cataloguesByRestaurantId(restId);
    }


}
