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

    @GetMapping("/catalogue/{catId}")
    public List<Catalogue> catalogue(@PathVariable String restId) {
        return service.cataloguesByRestaurantId(restId);
    }

    @PostMapping("/catalogue")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Catalogue addCatalogue(@RequestBody Catalogue catalogue) {
        return service.save(catalogue);
    }

    @DeleteMapping("/catalogue/{catId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCatalogue(@PathVariable String catId) {
        service.delete(catId);
    }

    @PutMapping("/catalogue")
    @ResponseStatus(code = HttpStatus.OK)
    public Catalogue updateCatalogue(@RequestBody Catalogue catalogue) {
        return service.update(catalogue);
    }

    @GetMapping("/{restId}/catalogue")
    public List<Catalogue> catalogues(@PathVariable String restId) {
        return service.cataloguesByRestaurantId(restId);
    }


}
