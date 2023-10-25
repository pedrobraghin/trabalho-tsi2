package com.example.trabalho_tsi2.dishes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.trabalho_tsi2.R;

public class DishCardFragment extends Fragment {
    private TextView cardTitleTextView;
    private TextView cardDescriptionTextView;

    private ImageView cardImageView;
    private String imagePath;

    private String cardTitle;
    private String cardDescription;
    public DishCardFragment() {

    }

    public static DishCardFragment newInstance(String cardTitle, String cardDescription, String imagePath) {
        DishCardFragment fragment = new DishCardFragment();
        Bundle args = new Bundle();
        args.putString("card_title", cardTitle);
        args.putString("card_description", cardDescription);
        args.putString("image_path", imagePath);

        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish_card, container, false);

        String cardTitle = getArguments().getString("card_title");
        String cardDescription = getArguments().getString("card_description");
        String imagePath =getArguments().getString("image_path");

        this.cardDescriptionTextView = view.findViewById(R.id.cardDescriptionTextView);
        this.cardDescriptionTextView.setText(cardDescription);

        this.cardTitleTextView = view.findViewById(R.id.cardTitleTextView);
        this.cardTitleTextView.setText(cardTitle);

        this.cardImageView = view.findViewById(R.id.cardImageView);

        setImage(imagePath, this.cardImageView);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setImage(String imagePath, ImageView cardImageView) {
        Glide.with(this).load(imagePath).into(cardImageView);
    }

}
