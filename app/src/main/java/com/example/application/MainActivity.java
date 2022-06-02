package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.firebaseAuthentication = FirebaseAuth.getInstance();
        this.redirectToHomeScreenIfLoggedIn();

        Button registerBtn = findViewById(R.id.registerBtn);
        Button loginBtn = findViewById(R.id.logInBtn);

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    public void redirectToHomeScreenIfLoggedIn(){
        boolean isLoggedIn = firebaseAuthentication.getCurrentUser() != null;
        if (isLoggedIn){
            this.redirectToScreen(HomeActivity.class);
        }
    }

    public void redirectToScreen(Class screen){
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerBtn: this.redirectToScreen(RegisterActivity.class); break;
            case R.id.logInBtn: this.redirectToScreen(LoginActivity.class); break;
            default: break;
        }
    }
}