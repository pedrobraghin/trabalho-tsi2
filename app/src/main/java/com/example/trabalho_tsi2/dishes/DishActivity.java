package com.example.trabalho_tsi2.dishes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;
import com.google.android.material.snackbar.Snackbar;

public class DishActivity extends AppCompatActivity {

    private TextView dishTypeText;
    private ImageView dishImageView;
    private TextView dishTypeTableText;
    private TextView dishTitleText;
    private TextView baseDishText;
    private TextView garnishText;
    private TextView saladText;
    private TextView dessertText;

    private Button buyTicketButton;

    private Dish dish;
    private boolean isBought = false;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        this.database = Database.getInstance();
        this.dishTypeText = findViewById(R.id.dishTypeText);
        this.dishImageView = findViewById(R.id.dishImageView);
        this.dishTypeTableText = findViewById(R.id.dishTypeTableText);
        this.dishTitleText = findViewById(R.id.dishTitleText);
        this.baseDishText = findViewById(R.id.baseDishText);
        this.garnishText = findViewById(R.id.garnishText);
        this.saladText = findViewById(R.id.saladText);
        this.dessertText = findViewById(R.id.dessertText);
        this.buyTicketButton = findViewById(R.id.buyTicketButton);

        Intent intent = getIntent();
        this.dish = (Dish) intent.getSerializableExtra("dish");

        setTexts();
        setImage();

        this.buyTicketButton.setOnClickListener(view -> {
            this.database.buyDish(dish);
            this.buyTicketButton.setText(R.string.ticket_bought);
            this.buyTicketButton.setEnabled(false);
            Snackbar.make(findViewById(R.id.dishConstraintLayout), "Ficha comprada com sucesso!", Snackbar.LENGTH_SHORT).show();
        });
    }

    private void setTexts() {
        String dishTypeString = "";

        switch (dish.getType()) {
            case MAIN:
                dishTypeString = "Prato principal";
                break;
            case VEGETARIAN:
                dishTypeString = "Opção vegetariana";
                break;
        }

        this.dishTypeText.setText(dishTypeString);
        this.dishTypeTableText.setText(dishTypeString);
        this.dishTitleText.setText(dish.getTitle());
        this.baseDishText.setText(String.join("\n", dish.getBaseDish()));
        this.garnishText.setText(dish.getGarnish());
        this.saladText.setText(dish.getSalad());
        this.dessertText.setText(dish.getDessert());
    }

    private void setImage() {
        Glide.with(this).load(dish.getImagePath()).into(this.dishImageView);
    }

}