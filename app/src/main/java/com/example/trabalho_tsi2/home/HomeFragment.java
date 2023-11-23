package com.example.trabalho_tsi2.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;
import com.example.trabalho_tsi2.dishes.Dish;
import com.example.trabalho_tsi2.dishes.DishActivity;
import com.example.trabalho_tsi2.dishes.DishCardFragment;
import com.example.trabalho_tsi2.purchases.Purchase;
import com.example.trabalho_tsi2.utils.RestaurantUtils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageButton mainDishImageButton;
    ImageButton vegetarianDishImageButton;
    Database database;
    ArrayList<Purchase> purchases;

    public HomeFragment() {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.updateRestaurantStatus(view.findViewById(R.id.restaurantStatusLabel));
        this.database = Database.getInstance();
        this.purchases = database.getPurchaseHistory();

        this.mainDishImageButton = view.findViewById(R.id.mainDishButton);
        this.vegetarianDishImageButton = view.findViewById(R.id.vegetarianDishButton);

        loadMainDishes();
        loadVegetarianDishes();

        this.mainDishImageButton.setOnClickListener(buttonView -> {
            Intent intent = new Intent(getActivity(), DishActivity.class);
            Dish mainDish = database.getDailyMainDish();
            intent.putExtra("dish", mainDish);
            requireActivity().startActivity(intent);
        });

        this.vegetarianDishImageButton.setOnClickListener(buttonView -> {
            Intent intent = new Intent(getActivity(), DishActivity.class);
            Dish mainDish = database.getDailyVegetarianDish();
            intent.putExtra("dish", mainDish);
            requireActivity().startActivity(intent);
        });

        return view;
    }

    private void loadMainDishes() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Dish[] mainDishes = this.database.getMainDishes();

        for (int i = 0; i < 7; i++) {
            Dish dish = mainDishes[i];
            DishCardFragment fragment = DishCardFragment.newInstance(RestaurantUtils.getWeekday(i), dish);
            fragmentTransaction.add(R.id.mainDishesContainer, fragment);
        }
        fragmentTransaction.commit();
    }

    private void loadVegetarianDishes() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Dish[] vegetarianDishes = this.database.getVegetarianDishes();
        for (int i = 0; i < 7; i++) {
            Dish dish = vegetarianDishes[i];
            DishCardFragment fragment = DishCardFragment.newInstance(RestaurantUtils.getWeekday(i), dish);
            fragmentTransaction.add(R.id.vegetarianDishesContainer, fragment);
        }
        fragmentTransaction.commit();
    }

    private void updateRestaurantStatus(TextView statusTextView) {
        if (RestaurantUtils.isRestaurantOpen()) {
            statusTextView.setText("Aberto agora");
            statusTextView.setTextColor(getResources().getColor(R.color.light_green, Resources.getSystem().newTheme()));
        } else {
            statusTextView.setText("Fechado");
            statusTextView.setTextColor(getResources().getColor(R.color.light_red, Resources.getSystem().newTheme()));
        }
    }


}
