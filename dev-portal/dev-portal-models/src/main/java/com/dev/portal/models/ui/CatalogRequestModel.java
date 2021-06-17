package com.dev.portal.models.ui;

public class CatalogRequestModel {

    private String name;
    private String catalog;
    private String count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CatalogRequestModel [name=" + name + ", catalog=" + catalog + ", count=" + count + "]";
    }

}
