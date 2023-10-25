package com.example.trabalho_tsi2.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;
import com.example.trabalho_tsi2.dishes.Dish;
import com.example.trabalho_tsi2.dishes.DishActivity;
import com.example.trabalho_tsi2.dishes.DishCardFragment;

public class HomeActivity extends AppCompatActivity {
    ImageButton mainDishImageButton;
    ImageButton vegetarianDishImageButton;

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    Database database = Database.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.mainDishImageButton = findViewById(R.id.mainDishButton);
        this.vegetarianDishImageButton= findViewById(R.id.vegetarianDishButton);

        this.loadMainDishes();
        this.loadVegetarianDishes();

        fragmentTransaction.commit();

        this.mainDishImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, DishActivity.class);
            Dish mainDish = database.getDailyMainDish();
            intent.putExtra("dish", mainDish);
            startActivity(intent);
        });

        this.vegetarianDishImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, DishActivity.class);
            Dish mainDish = database.getDailyVegetarianDish();
            intent.putExtra("dish", mainDish);
            startActivity(intent);
        });
    }

    private void loadMainDishes() {
        Dish[] mainDishes = this.database.getMainDishes();

        for (int i = 0; i < 7; i++) {
            Dish dish = mainDishes[i];
            DishCardFragment fragment = DishCardFragment.newInstance(this.getWeekday(i), dish);
            fragmentTransaction.add(R.id.mainDishesContainer, fragment);
        }
    }

    private void loadVegetarianDishes() {
        Dish[] vegetarianDishes = this.database.getVegetarianDishes();
        for (int i = 0; i < 7; i++) {
            Dish dish = vegetarianDishes[i];
            DishCardFragment fragment = DishCardFragment.newInstance(this.getWeekday(i), dish);
            fragmentTransaction.add(R.id.vegetarianDishesContainer, fragment);
        }
    }

    private String getWeekday(int index) {
        switch (index) {
            case 0:
                return "Segunda";
            case 1:
                return "Terça";
            case 2:
                return "Quarta";
            case 3:
                return "Quinta";
            case 4:
                return "Sexta";
            case 5:
                return "Sábado";
            case 6:
                return "Domingo";
            default:
                return "Not found";
        }
    }
}