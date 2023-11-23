package com.example.trabalho_tsi2.dishes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.Database;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

public class BuyDishBottomSheetFragment extends BottomSheetDialogFragment {
    private final Dish dish;
    private final Database database = Database.getInstance();
    public BuyDishBottomSheetFragment(Dish dish) {
        this.dish = dish;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        view.findViewById(R.id.share_button).setOnClickListener(btnView -> {
            this.database.buyDish(dish);
            Snackbar.make(view, "Ficha comprada com sucesso!", Snackbar.LENGTH_SHORT).show();
        });
        return view;
    }
}
