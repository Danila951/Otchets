package com.company.entity;

import java.util.Objects;

public class ProductEntity {
    private int ID;
    private String title;
    private Double cost;
    private String description;
    private String mainImagePath;
    private boolean isActive;
    private int manufacturerID;

    public ProductEntity(int ID, String title, Double cost, String description, String mainImagePath, boolean isActive, int manufacturerID) {
        this.ID = ID;
        this.title = title;
        this.cost = cost;
        this.description = description;
        this.mainImagePath = mainImagePath;
        this.isActive = isActive;
        this.manufacturerID = manufacturerID;
    }

    public ProductEntity(String title, Double cost, String description, String mainImagePath, boolean isActive, int manufacturerID) {
        this.ID = -1;
        this.title = title;
        this.cost = cost;
        this.description = description;
        this.mainImagePath = mainImagePath;
        this.isActive = isActive;
        this.manufacturerID = manufacturerID;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return ID == that.ID && isActive == that.isActive && manufacturerID == that.manufacturerID && title.equals(that.title) && cost.equals(that.cost) && description.equals(that.description) && mainImagePath.equals(that.mainImagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, title, cost, description, mainImagePath, isActive, manufacturerID);
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImagePath() {
        return mainImagePath;
    }

    public void setMainImagePath(String mainImagePath) {
        this.mainImagePath = mainImagePath;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", MainImagePath='" + mainImagePath + '\'' +
                ", IsActive=" + isActive +
                ", ManufacturerID=" + manufacturerID +
                '}';
    }
}
