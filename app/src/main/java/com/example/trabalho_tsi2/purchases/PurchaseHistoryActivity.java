package com.example.trabalho_tsi2.purchases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.trabalho_tsi2.R;

import java.util.ArrayList;

public class PurchaseHistoryActivity extends AppCompatActivity {

    private ArrayList<Purchase> purchases;
    private PurchasesAdapter purchasesAdapter;
    private ListView purchasesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        this.purchases = (ArrayList<Purchase>) getIntent().getSerializableExtra("purchases");
        this.purchasesAdapter = new PurchasesAdapter(this, purchases);

        this.purchasesListView = findViewById(R.id.purchasesListView);
        this.purchasesListView.setAdapter(purchasesAdapter);
    }
}