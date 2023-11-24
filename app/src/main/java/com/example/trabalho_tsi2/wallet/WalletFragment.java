package com.example.trabalho_tsi2.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.DatabaseSingleton;
import com.example.trabalho_tsi2.profile.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class WalletFragment extends Fragment {

    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final DatabaseReference profiles = DatabaseSingleton.getInstance().profiles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        TextView balanceText = view.findViewById(R.id.balanceText);
        Button rechargeButton = view.findViewById(R.id.rechargeButton);

        rechargeButton.setOnClickListener(buttonView -> {
            Intent intent = new Intent(getContext(), RechargeWalletActivity.class);
            startActivity(intent);
        });

        String uid = auth.getUid();
        assert uid != null;
        DatabaseReference profileReference = profiles.child(uid);
        profileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Profile profile = snapshot.getValue(Profile.class);
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
                    decimalFormatSymbols.setDecimalSeparator(',');
                    decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
                    balanceText.setText(String.format("%s%s", getString(R.string.real_symbol), decimalFormat.format(profile.getBalance())));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
