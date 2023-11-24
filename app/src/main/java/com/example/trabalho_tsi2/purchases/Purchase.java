package com.example.trabalho_tsi2.purchases;

import com.example.trabalho_tsi2.dishes.Dish;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Purchase implements Serializable {
    private Dish dish;
    private Date date;
    private boolean chipAvailable;

    private final int id;

    public Purchase(Dish dish, Date date) {
        this.dish = dish;
        this.date = date;
        this.chipAvailable = true;
        this.id = this.generateId();
    }

    private int generateId() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getChipAvailable() { return chipAvailable; };

    public void useChip() { this.chipAvailable = false; }

    public int getId() { return this.id; }
}
