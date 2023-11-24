package com.example.trabalho_tsi2.signup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.DatabaseSingleton;
import com.example.trabalho_tsi2.profile.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignupActivity extends AppCompatActivity {

    private EditText fullnameInput;
    private EditText rgaInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button confirmSignupButton;

    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.fullnameInput = findViewById(R.id.fullnameInput);
        this.rgaInput = findViewById(R.id.rgaInput);
        this.emailInput = findViewById(R.id.emailInput);
        this.passwordInput = findViewById(R.id.passwordInput);
        this.confirmSignupButton = findViewById(R.id.confirmSignupButton);

        this.confirmSignupButton.setOnClickListener(view -> {
            String fullname = fullnameInput.getText().toString();
            String rga = rgaInput.getText().toString();
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            confirmSignupButton.setEnabled(false);
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, task -> {
                if (task.isSuccessful()) {
                    DatabaseReference profiles = DatabaseSingleton.getInstance().profiles;
                    Profile newProfile = new Profile(fullname, rga, 0.0);
                    String uid = auth.getUid();
                    assert uid != null;
                    profiles.child(uid).setValue(newProfile);
                    finish();
                }

                Toast toast = new Toast(getApplicationContext());
                toast.setText("Não foi possível realizar o cadastro!");
                toast.show();
            });
        });
    }
}