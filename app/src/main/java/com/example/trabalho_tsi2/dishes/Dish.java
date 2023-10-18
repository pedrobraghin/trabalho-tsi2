package com.example.trabalho_tsi2.dishes;

import java.io.Serializable;

public class Dish implements Serializable {
    private String title;
    private String[] baseDish;
    private String garnish;
    private String salad;
    private String dessert;
    private DishType type;
    private String imagePath;

    public Dish(String title, String[] baseDish, String garnish, String salad, String dessert, DishType type, String imagePath) {
        this.title = title;
        this.baseDish = baseDish;
        this.garnish = garnish;
        this.salad = salad;
        this.dessert = dessert;
        this.type = type;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getBaseDish() {
        return baseDish;
    }

    public void setBaseDish(String[] baseDish) {
        this.baseDish = baseDish;
    }

    public String getGarnish() {
        return garnish;
    }

    public void setGarnish(String garnish) {
        this.garnish = garnish;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
