package com.example.trabalho_tsi2.purchases;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ChipsHistoryPagerAdapter extends FragmentStateAdapter {

    public ChipsHistoryPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;

        if (position == 0) {
            fragment = new PurchaseHistoryFragment();
        } else {
            fragment = new UsageHistoryFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
