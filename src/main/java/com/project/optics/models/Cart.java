package com.project.optics.models;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_count")
    private int itemCount;

    @Column(name = "item_parameters")
    private String itemParameters;

    public Cart() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public String getItemParameters() {
        return itemParameters;
    }
    public void setItemParameters(String itemParameters) {
        this.itemParameters = itemParameters;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", itemCount=" + itemCount +
                '}';
    }
}
