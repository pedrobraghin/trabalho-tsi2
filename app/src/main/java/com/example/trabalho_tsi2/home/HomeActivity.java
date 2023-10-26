package com.example.trabalho_tsi2.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.trabalho_tsi2.MainActivity;
import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;
import com.example.trabalho_tsi2.dishes.Dish;
import com.example.trabalho_tsi2.dishes.DishActivity;
import com.example.trabalho_tsi2.dishes.DishCardFragment;
import com.example.trabalho_tsi2.purchases.Purchase;
import com.example.trabalho_tsi2.purchases.PurchaseHistoryActivity;

import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    ImageButton mainDishImageButton;
    ImageButton vegetarianDishImageButton;

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    Database database = Database.getInstance();

    ArrayList<Purchase> purchases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mainDishImageButton = findViewById(R.id.mainDishButton);
        this.vegetarianDishImageButton = findViewById(R.id.vegetarianDishButton);

        loadMainDishes();
        loadVegetarianDishes();
        fragmentTransaction.commit();

        this.mainDishImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, DishActivity.class);
            Dish mainDish = database.getDailyMainDish();
            intent.putExtra("dish", mainDish);
            startActivityForResult(intent, 1);
        });

        this.vegetarianDishImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, DishActivity.class);
            Dish mainDish = database.getDailyVegetarianDish();
            intent.putExtra("dish", mainDish);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.historyMenuItem) {
            Intent intent = new Intent(this, PurchaseHistoryActivity.class);
            intent.putExtra("purchases", purchases);
            startActivity(intent);
            return true;
        }

        if (itemId == R.id.exitMenuItem) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                Dish dish = null;
                dish = (Dish) data.getSerializableExtra("dish");
                this.purchases.add(0, new Purchase(dish, new Date()));
            }
        }
    }
}