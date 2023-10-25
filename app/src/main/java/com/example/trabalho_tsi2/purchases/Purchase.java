package com.example.trabalho_tsi2.purchases;

import com.example.trabalho_tsi2.dishes.Dish;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    private Dish dish;
    private Date date;

    public Purchase(Dish dish, Date date) {
        this.dish = dish;
        this.date = date;
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
}
