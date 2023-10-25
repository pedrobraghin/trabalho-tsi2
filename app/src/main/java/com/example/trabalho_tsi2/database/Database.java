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
        String[] baseDish = {"Peito de frango empanado", "Batata cozida", "Molho de tomate"};
        Dish mainDish = new Dish(
                "Frango empanado",
                baseDish,
                "Frango empanado",
                "Repolho com tomate",
                "Laranja",
                DishType.MAIN,
                "https://static.itdg.com.br/images/1200-630/060ff779acb3a3ce1a171699ff8d4070/328929-original.jpg"
        );

        return mainDish;
    }

    public Dish getDailyVegetarianDish() {
        String[] baseDish = {"Cenoura", "Ervilha", "Queijo", "Batata palha"};
        Dish vegetarianDish = new Dish(
                "Panqueca de legumes",
                baseDish,
                "Panqueca de legumes",
                "Repolho com tomate",
                "Laranja",
                DishType.VEGETARIAN,
                "https://www.receitasnestle.com.br/sites/default/files/srh_recipes/e2f5a9b5191f3e1d4a5f9ea1ea7a749b.jpg"
        );

        return vegetarianDish;
    }

    public Dish[] getMainDishes() {
        String[] baseDish = {"Cenoura", "Ervilha", "Queijo", "Batata palha"};
        Dish mainDish = new Dish(
                "Panqueca de legumes",
                baseDish,
                "Panqueca de legumes",
                "Repolho com tomate",
                "Laranja",
                DishType.VEGETARIAN,
                "https://www.receitasnestle.com.br/sites/default/files/srh_recipes/e2f5a9b5191f3e1d4a5f9ea1ea7a749b.jpg"
        );

        Dish[] dishes ={ mainDish, mainDish, mainDish, mainDish, mainDish, mainDish, mainDish };

        return dishes;
    }

    public Dish[] getVegetarianDishes() {
        String[] baseDish = {"Cenoura", "Ervilha", "Queijo", "Batata palha"};
        Dish vegetarianDish = new Dish(
                "Panqueca de legumes",
                baseDish,
                "Panqueca de legumes",
                "Repolho com tomate",
                "Laranja",
                DishType.VEGETARIAN,
                "https://www.receitasnestle.com.br/sites/default/files/srh_recipes/e2f5a9b5191f3e1d4a5f9ea1ea7a749b.jpg"
        );

        Dish[] dishes ={ vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish, vegetarianDish };

        return dishes;
    }
}
