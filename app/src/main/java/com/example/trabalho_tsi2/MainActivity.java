package com.example.trabalho_tsi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.trabalho_tsi2.databinding.ActivityMainBinding;
import com.example.trabalho_tsi2.home.HomeFragment;
import com.example.trabalho_tsi2.profile.ProfileFragment;
import com.example.trabalho_tsi2.purchases.ChipsHistoryFragment;
import com.example.trabalho_tsi2.wallet.WalletFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                replaceFragment(new HomeFragment());
                return true;
            }

            if (itemId == R.id.navigation_history) {
                replaceFragment(new ChipsHistoryFragment());
                return true;
            }

            if (itemId == R.id.navigation_wallet) {
                replaceFragment(new WalletFragment());
                return true;
            }

            if (itemId == R.id.navigation_profile) {
                replaceFragment(new ProfileFragment());
                return true;
            }

            return false;
        });

        replaceFragment(new HomeFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}