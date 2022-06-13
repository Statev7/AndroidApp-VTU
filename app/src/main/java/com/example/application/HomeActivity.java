package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private final Integer MIN_QUESTIONS_COUNT = 3;
    private final Integer MAX_QUESTIONS_COUNT = 5;
    private final String QUESTIONS_RANGE_ERROR_MESSAGE = "Questions should be in the range of 3 - 5";
    private final String EMPTY_QUESTION_COUNT_FIELD = "Question field cannot be empty!";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.firebaseAuth = FirebaseAuth.getInstance();
        EditText numberOfQuestionsInput = findViewById(R.id.numberOfQue);
        Button startGameBtn = findViewById(R.id.startGame);
        Spinner dropdown = findViewById(R.id.spinner);

        Date date = new Date();
        String[] categories = date.getCategories();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, categories);

        dropdown.setAdapter(adapter);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numberAsString = numberOfQuestionsInput.getText().toString();
                if(!numberAsString.isEmpty()){

                    Integer count = Integer.parseInt(numberAsString);
                    boolean isRangeInvalid = MIN_QUESTIONS_COUNT > count || count > MAX_QUESTIONS_COUNT;

                    if (isRangeInvalid){
                        Toast.makeText(HomeActivity.this, QUESTIONS_RANGE_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String category = dropdown.getSelectedItem().toString();

                    Intent intent = new Intent(HomeActivity.this, GameActivity.class);
                    intent.putExtra("QuestionCount", count);
                    intent.putExtra("Category", category);
                    startActivity(intent);

                } else {
                    Toast.makeText(HomeActivity.this, EMPTY_QUESTION_COUNT_FIELD, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void redirectToScreen(Class screen){
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutItem:
                this.firebaseAuth.signOut();
                this.redirectToScreen(MainActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}