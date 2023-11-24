package com.example.trabalho_tsi2.purchases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;

import java.util.ArrayList;

public class UsageHistoryFragment extends Fragment implements PurchaseObserver{

    private PurchasesAdapter purchasesAdapter;
    private Database database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usage_history, container, false);

        database = Database.getInstance();
        database.addObserver(this);

        ArrayList<Purchase> purchases = database.getPurchaseHistory();
        ArrayList<Purchase> usedPurchases = new ArrayList<>();

        for(Purchase purchase: purchases) {
            if (purchase.getChipAvailable()) {
                continue;
            }
            usedPurchases.add(purchase);
        }

        purchasesAdapter = new PurchasesAdapter(getActivity(), usedPurchases);

        ListView purchasesListView = view.findViewById(R.id.purchasesListView);
        purchasesListView.setAdapter(purchasesAdapter);

        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.database.removeObserver(this);
    }
    @Override
    public void onDataChanged() {
        this.purchasesAdapter.notifyDataSetChanged();
    }
}
