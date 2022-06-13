package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.stats = findViewById(R.id.stats);
        this.setStats();

        Button startNewGameBtn = findViewById(R.id.startNewGame);
        startNewGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setStats(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String stats = bundle.get("Stats").toString();

        this.stats.setText(stats);
    }
}