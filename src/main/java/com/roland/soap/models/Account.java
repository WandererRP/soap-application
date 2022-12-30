package com.roland.soap.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author Roland Pilpani 29.12.2022
 */
@Entity
public class Account {
    @Id
    private long id;

    private String name;
    private double sum;

    public Account(long id, String name, double sum) {
        this.id = id;
        this.name = name;
        this.sum = sum;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
