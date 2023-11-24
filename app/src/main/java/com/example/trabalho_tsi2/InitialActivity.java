package com.example.trabalho_tsi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho_tsi2.signup.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class InitialActivity extends AppCompatActivity {

    private Button signupButton ;
    private Button signinButton;
    private EditText emailInput;
    private EditText passwordInput;
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (auth.getCurrentUser() != null) {
            startMainActivity();
        }

        setContentView(R.layout.activity_initial);

        signupButton = findViewById(R.id.signupButton);
        signinButton = findViewById(R.id.signinButton);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

        signinButton.setOnClickListener(view -> {
            signinButton.setEnabled(false);
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    startMainActivity();
                } else {
                    Exception exception = task.getException();
                    Toast toast = new Toast(getApplicationContext());

                    if (exception instanceof FirebaseAuthException) {
                        toast.setText("E-mail ou senha incorretos!");
                    } else {
                        toast.setText("Não foi possível realizar o cadastro!");
                    }

                    toast.show();
                    signinButton.setEnabled(true);
                }
            });
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}