package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final int PASSWORD_MIN_LENGTH = 5;
    private final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password should be longer than 5 symbols!";
    private final String PASSWORD_NOT_MATCH_ERROR_MESSAGE = "Password should match repeat password!";
    private final String SUCCESSFULLY_REGISTER_MESSAGE = "Successful register!";
    private final String EMPTY_FIELDS_ERROR_MESSAGE = "Some fields are empty!";

    private FirebaseAuth firebaseAuthentication;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.firebaseAuthentication = FirebaseAuth.getInstance();

        Button registerBtn = findViewById(R.id.registerButton);
        TextView loginLink = findViewById(R.id.loginLink);

        registerBtn.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    public void register(){

        EditText emailInput = findViewById(R.id.email);
        EditText passwordInput = findViewById(R.id.password);
        EditText repeatPasswordInput = findViewById(R.id.repeatPassword);

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String repeatPassword = repeatPasswordInput.getText().toString();

        boolean isNotEmpty = !email.isEmpty() && !password.isEmpty() && !repeatPassword.isEmpty();
        if (isNotEmpty){

            if (password.length() <= PASSWORD_MIN_LENGTH){
                Toast.makeText(RegisterActivity.this, PASSWORD_LENGTH_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.equals(repeatPassword) == false) {
                Toast.makeText(RegisterActivity.this, PASSWORD_NOT_MATCH_ERROR_MESSAGE,
                        Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuthentication.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(RegisterActivity.this, SUCCESSFULLY_REGISTER_MESSAGE,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(RegisterActivity.this, EMPTY_FIELDS_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    public void redirectToScreen(Class screen){
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerButton: this.register(); break;
            case R.id.loginLink: this.redirectToScreen(LoginActivity.class); break;
        }
    }
}