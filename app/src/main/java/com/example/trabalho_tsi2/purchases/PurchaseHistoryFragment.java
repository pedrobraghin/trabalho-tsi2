package com.example.trabalho_tsi2.purchases;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;

import java.util.ArrayList;

public class PurchaseHistoryFragment extends Fragment {

    private ArrayList<Purchase> purchases;
    private Database database;
    private PurchasesAdapter purchasesAdapter;
    private ListView purchasesListView;


    public PurchaseHistoryFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase_history, container, false);

        this.database = Database.getInstance();
        this.purchases = database.getPurchaseHistory();

        this.purchasesAdapter = new PurchasesAdapter(getActivity(), purchases);

        this.purchasesListView = view.findViewById(R.id.purchasesListView);
        this.purchasesListView.setAdapter(purchasesAdapter);

        return view;
    }
}