package com.lab.catalogue.domain;


import java.util.List;
import java.util.UUID;

public class Catalogue {

    private String id;

    private String version;

    private List<SubCatalogue> menus;

    private String description;

    public boolean current;

    public Catalogue() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<SubCatalogue> getMenus() {
        return menus;
    }

    public void setMenus(List<SubCatalogue> menus) {
        this.menus = menus;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Catalogue desactivate() {
        this.setCurrent(false);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalogue catalogue = (Catalogue) o;

        return id.equals(catalogue.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
