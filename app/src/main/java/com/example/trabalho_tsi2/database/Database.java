package com.example.trabalho_tsi2.database;

import com.example.trabalho_tsi2.dishes.Dish;
import com.example.trabalho_tsi2.dishes.DishType;

public class Database {
    private static Database instance;

    public static Database getInstance() {
        if(Database.instance == null) {
            Database.instance = new Database();
        }

        return Database.instance;
    }

    public Dish getDailyMainDish() {
        String[] baseDish = {"Arroz branco", "Arroz integral", "Feijão com caldo"};
        Dish mainDish = new Dish(
                "Filé de frango acebolado",
                baseDish,
                "Farofa",
                "Alface e cenoura",
                "Laranja",
                DishType.MAIN,
                "https://oquetempracomer.com.br/wp-content/uploads/2022/02/Bife-de-frango-acebolado-capa.jpg"
        );

        return mainDish;
    }

    public Dish getDailyVegetarianDish() {
        String[] baseDish = {"Arroz branco", "Arroz integral", "Feijão com caldo"};
        Dish vegetarianDish = new Dish(
                "Lentilha com legumes",
                baseDish,
                "Farofa",
                "Alface e cenoura",
                "Laranja",
                DishType.VEGETARIAN,
                "https://www.saboresajinomoto.com.br/uploads/images/recipes/lentilha-com-legumes.jpg"
        );

        return vegetarianDish;
    }

    public Dish[] getMainDishes() {
        String[] baseDish = {"Arroz branco", "Arroz integral", "Feijão com caldo"};
        Dish mainDish = new Dish(
                "Filé de frango acebolado",
                baseDish,
                "Farofa",
                "Alface e cenoura",
                "Laranja",
                DishType.MAIN,
                "https://oquetempracomer.com.br/wp-content/uploads/2022/02/Bife-de-frango-acebolado-capa.jpg"
        );

        Dish[] dishes ={ mainDish, mainDish, mainDish, mainDish, mainDish, mainDish, mainDish };

        return dishes;
    }

    public Dish[] getVegetarianDishes() {
        String[] baseDish = {"Arroz branco", "Arroz integral", "Feijão com caldo"};
        Dish vegetarianDish = new Dish(
                "Lentilha com legumes",
                baseDish,
                "Farofa",
                "Alface e cenoura",
                "Laranja",
                DishType.VEGETARIAN,
                "https://www.saboresajinomoto.com.br/uploads/images/recipes/lentilha-com-legumes.jpg"
        );

        Dish[] dishes ={ vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish };

        return dishes;
    }
}
