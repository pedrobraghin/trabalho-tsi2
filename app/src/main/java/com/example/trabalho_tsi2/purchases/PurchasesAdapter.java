package com.example.trabalho_tsi2.purchases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.dishes.Dish;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PurchasesAdapter extends ArrayAdapter<Purchase> {

    private final Context context;
    private final ArrayList<Purchase> purchases;

    public PurchasesAdapter(@NonNull Context context, ArrayList<Purchase> purchases) {
        super(context, R.layout.purchase_list_item, purchases);
        this.context = context;
        this.purchases = purchases;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        @SuppressLint("ViewHolder") View itemView = layoutInflater.inflate(R.layout.purchase_list_item, parent, false);

        ImageView dishImageView = itemView.findViewById(R.id.purchaseImageView);
        TextView purchaseDishText = itemView.findViewById(R.id.purchaseDishText);
        TextView purchaseDateText = itemView.findViewById(R.id.purchaseDateText);

        Purchase purchase = purchases.get(position);
        Dish dish = purchase.getDish();
        Date date = purchase.getDate();

        Glide.with(context).load(dish.getImagePath()).into(dishImageView);
        dishImageView.setContentDescription("Imagem de um " + dish.getTitle());

        purchaseDishText.setText(dish.getTitle());

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        purchaseDateText.setText(dateFormat.format(date));

        return itemView;
    }
}
