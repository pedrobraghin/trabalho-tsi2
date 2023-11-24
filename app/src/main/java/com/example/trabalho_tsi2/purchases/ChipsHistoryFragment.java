package com.example.trabalho_tsi2.purchases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.trabalho_tsi2.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChipsHistoryFragment extends Fragment {
    ChipsHistoryPagerAdapter chipsHistoryPagerAdapter;
    ViewPager2 viewPager;

    private final String[] tabTitles = {"Fichas não utilizadas", "Fichas utilizadas"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chips_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        chipsHistoryPagerAdapter = new ChipsHistoryPagerAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(chipsHistoryPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabTitles[position])
        ).attach();
    }

}
