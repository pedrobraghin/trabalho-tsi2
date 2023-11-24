package com.example.trabalho_tsi2.wallet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.DatabaseSingleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class RechargeWalletActivity extends AppCompatActivity {
    private EditText walletRechargeInput;
    private Button confirmRechargeButton;

    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final DatabaseReference profiles = DatabaseSingleton.getInstance().profiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_wallet);

        this.walletRechargeInput = findViewById(R.id.walletRechargeInput);
        this.confirmRechargeButton = findViewById(R.id.confirmRechargeButton);

        confirmRechargeButton.setOnClickListener(view -> {
            confirmRechargeButton.setEnabled(false);
            double rechargeValue = Double.parseDouble(walletRechargeInput.getText().toString());
            String uid = auth.getUid();
            assert uid != null;
            profiles.child(uid).child("balance").runTransaction(new Transaction.Handler() {
                @NonNull
                @Override
                public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                    double value = currentData.getValue(Double.class);
                    value += rechargeValue;
                    currentData.setValue(value);
                    return Transaction.success(currentData);
                }

                @Override
                public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                    if (committed) {
                        finish();
                    } else {
                        Toast toast = new Toast(getApplicationContext());
                        toast.setText("Não foi possível recarregar a carteira!");
                        toast.show();
                        confirmRechargeButton.setEnabled(true);
                    }
                }
            });
        });
    }
}