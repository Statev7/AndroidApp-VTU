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

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String EMPTY_FIELDS_ERROR_MESSAGE = "Some fields are empty!";
    private final String INVALID_CREDENTIALS_ERROR_MESSAGE = "Invalid credentials!";
    private final String SUCCESSFULLY_LOGGED_IN_MESSAGE = "You have successfully logged in!";

    private FirebaseAuth firebaseAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.firebaseAuthentication = FirebaseAuth.getInstance();

        Button loginBtn = findViewById(R.id.logInButton);
        TextView registerLink = findViewById(R.id.registerLink);

        loginBtn.setOnClickListener(this);
        registerLink.setOnClickListener(this);
    }

    public void login(){

        TextView emailInput = findViewById(R.id.loginEmail);
        TextView passwordInput = findViewById(R.id.loginPassword);

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        boolean isNotEmpty = !email.isEmpty() && !email.isEmpty();

        if (isNotEmpty){

            this.firebaseAuthentication.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, SUCCESSFULLY_LOGGED_IN_MESSAGE, Toast.LENGTH_SHORT).show();
                                redirectToScreen(HomeActivity.class);
                            } else {
                                Toast.makeText(LoginActivity.this, INVALID_CREDENTIALS_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else{
            Toast.makeText(LoginActivity.this, EMPTY_FIELDS_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    public void redirectToScreen(Class screen){
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logInButton: this.login(); break;
            case R.id.registerLink: this.redirectToScreen(RegisterActivity.class); break;
        }
    }
}