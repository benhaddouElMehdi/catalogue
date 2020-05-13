package com.lab.catalogue.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class Restaurant {
    @Id
    private String id;

    private String name;

    private Address address;

    private List<Catalogue> catalogues;

    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Restaurant() {
        id = UUID.randomUUID().toString();
    }

    public Restaurant addCatalogue(Catalogue catalogue) {
        if (catalogue.isCurrent()) {
            desactivateCurrentCatalogue();
        }
        this.catalogues.add(catalogue);
        return this;
    }

    public Restaurant activateCatalogue(String id) {
        desactivateCurrentCatalogue();
        this.catalogues.stream()
                .filter(catalogue -> catalogue.getId().equals(id))
                .findFirst()
                .get()
                .setCurrent(true);
        return this;
    }

    public void desactivateCurrentCatalogue() {
        this.catalogues.stream().filter(Catalogue::isCurrent).findFirst().map(Catalogue::desactivate);
    }

    public void deleteCatalogue(String catId) {
        List<Catalogue> catalogues = this.getCatalogues()
                .stream()
                .filter(catalogue -> !catalogue.getId().equals(catId))
                .collect(Collectors.toList());
        this.setCatalogues(catalogues);
    }

    public void override(Catalogue catalogue) {
        this.getCatalogues().remove(catalogue);
        this.addCatalogue(catalogue);
    }

    public Optional<Catalogue> currentCatalogue() {
        return this.getCatalogues()
                .stream()
                .filter(Catalogue::isCurrent)
                .findFirst();
    }

    public Optional<Catalogue> catalogue(String catId) {
        return this.getCatalogues()
                .stream()
                .filter(catalogue -> catalogue.getId().equals(catId))
                .findFirst();
    }
}