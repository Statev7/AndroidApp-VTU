package com.example.application;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private TextView questionTitle;
    private RadioGroup group;
    private Button button;
    private int currentQuestion = 1;
    private int randomIndex = 0;
    private int questionCount;
    private int score = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.questions = new ArrayList<>();
        this.questionTitle = findViewById(R.id.questionTitle);
        this.group = findViewById(R.id.group);
        this.button = findViewById(R.id.submitBtn);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        this.questionCount = (Integer) b.get("QuestionCount");
        String category = b.get("Category").toString();

        Date date = new Date();
        this.questions = date.getDate();

        if (!category.equals("None")){
            this.questions.removeIf(q -> !q.getCategory().equals(category));
        }

        this.loadQuestion();
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz();
            }
        });
    }

    public void quiz(){

        int id = this.group.getCheckedRadioButtonId();
        if(id == -1){
            Toast.makeText(GameActivity.this, "Chose answer!", Toast.LENGTH_SHORT).show();
            return;
        }
        checkAnswer(id);

        boolean isQuizEnd = this.currentQuestion >= this.questionCount;
        if(isQuizEnd){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Score", this.score);
            intent.putExtra("questionCount", this.questionCount);
            startActivity(intent);
            return;
        }

        this.currentQuestion++;
        this.group.clearCheck();
        loadQuestion();
    }

    public void checkAnswer(int id){

        RadioButton btn = findViewById(id);
        String answerValue = btn.getText().toString();
        boolean isEquals = answerValue.equals(this.questions.get(this.randomIndex).getCorrectAnswer());
        if(isEquals){
            this.score++;
        }
    }

    public void loadQuestion(){

        setRandomIndex();
        ArrayList<Question> filteredList = new ArrayList<>();

        for (int i = 0; i < this.questions.size(); i++) {
            Question currentQuestion = this.questions.get(i);
            if (currentQuestion.getIsUsed() == false){
                filteredList.add(currentQuestion);
            }
        }

        this.questions = filteredList;

        Question question = this.questions.get(this.randomIndex);
        question.setIsUsed(true);
        this.questions.set(randomIndex, question);

        List<String> answers = Arrays.asList(question.getA(), question.getB(), question.getC(), question.getD());
        this.questionTitle.setText(question.getQuestion());

        for (int index = 0; index < this.group.getChildCount(); index++) {
            RadioButton btn = (RadioButton) this.group.getChildAt(index);
            btn.setText(answers.get(index));
        }
    }

    public void setRandomIndex(){
        Random random = new Random();
        this.randomIndex = random.nextInt(this.questions.size() - 1);
    }
}