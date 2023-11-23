package com.example.trabalho_tsi2.dishes;

public class DishCardType {
    private String imagePath;
    private String cardTitle;
    private String cardDescription;

    public DishCardType(String imagePath, String cardTitle, String cardDescription) {
        this.imagePath = imagePath;
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }
}
