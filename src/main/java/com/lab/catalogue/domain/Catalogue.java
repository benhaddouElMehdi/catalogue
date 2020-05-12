package com.lab.catalogue.domain;


import java.util.List;
import java.util.UUID;

public class Catalogue {

    private String id;

    private String version;

    private List<SubMenu> menus;

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

    public List<SubMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SubMenu> menus) {
        this.menus = menus;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
