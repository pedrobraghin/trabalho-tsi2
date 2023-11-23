package com.example.trabalho_tsi2.dishes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;

public class DishActivity extends AppCompatActivity {

    private ImageView dishImageView;
    private TextView dishTypeTableText;
    private TextView dishTitleText;
    private TextView baseDishText;
    private TextView garnishText;
    private TextView saladText;
    private TextView dessertText;

    private Dish dish;

    private BuyDishBottomSheetFragment buyDishBottomSheetFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dish);

        Database database = Database.getInstance();
        this.dishImageView = findViewById(R.id.dishImageView);
        this.dishTypeTableText = findViewById(R.id.dishTypeTableText);
        this.dishTitleText = findViewById(R.id.dishTitleText);
        this.baseDishText = findViewById(R.id.baseDishText);
        this.garnishText = findViewById(R.id.garnishText);
        this.saladText = findViewById(R.id.saladText);
        this.dessertText = findViewById(R.id.dessertText);
        Button buyTicketButton = findViewById(R.id.buyTicketButton);

        Intent intent = getIntent();
        this.dish = (Dish) intent.getSerializableExtra("dish");

        setTexts();
        setImage();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null ){
            actionBar.setTitle(this.dish.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        this.buyDishBottomSheetFragment = new BuyDishBottomSheetFragment(dish);

        buyTicketButton.setOnClickListener(view -> {
            showBottomSheet();
        });
    }

    private void shareDish() {
        String dishDescription = dish.getTitle() + " " + dish.getType();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, dishDescription);
        startActivity(Intent.createChooser(intent, "Compartilhar via: "));
    }

    private void showBottomSheet() {
        this.buyDishBottomSheetFragment.show(getSupportFragmentManager(), buyDishBottomSheetFragment.getTag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_dish_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.share_dish) {
            shareDish();
            return true;
        }

        if (itemId == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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