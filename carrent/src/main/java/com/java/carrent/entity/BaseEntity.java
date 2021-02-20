package com.java.carrent.entity;

public class BaseEntity {

    private String name;
    private Double value;

    public BaseEntity() {
    }

    public BaseEntity(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double price) {
        this.value = price;
    }
}
