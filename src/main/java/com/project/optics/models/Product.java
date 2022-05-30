package com.project.optics.models;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column
    private String image, itemName;
    private int price, typeId;

    public Product() {}

    public Product(String itemName, String image, int price, int typeId) {
        this.image = image;
        this.itemName = itemName;
        this.price = price;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }
    public int getTypeId() {
        return typeId;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public String getCoverLink() {
        return image;
    }
    public void setCoverLink(String image) {
        this.image = image;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
