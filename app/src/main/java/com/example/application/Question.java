package com.example.application;

public class Question {

    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private String correctAnswer;
    private String category;
    private boolean isUsed;

    public Question(String question, String a, String b, String c, String d, String category, String correctAnswer){
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.isUsed = false;
    }

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() { return d; }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() { return category; }

    public boolean getIsUsed() {return isUsed;}

    public void setIsUsed(boolean used) {
        isUsed = used;
    }
}
