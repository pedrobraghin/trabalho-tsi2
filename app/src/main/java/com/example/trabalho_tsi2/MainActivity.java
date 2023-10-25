package com.example.trabalho_tsi2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.trabalho_tsi2.about.AboutActivity;
import com.example.trabalho_tsi2.home.HomeActivity;
import com.example.trabalho_tsi2.purchases.PurchaseHistoryActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
    private Button aboutButton;

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.continueButton = findViewById(R.id.continueButton);
        this.aboutButton = findViewById(R.id.aboutButton);

        this.continueButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        this.aboutButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });
    }
}