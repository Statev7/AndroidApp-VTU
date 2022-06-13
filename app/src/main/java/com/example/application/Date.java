package com.example.application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Date {

    public ArrayList<Question> getDate(){

        ArrayList<Question> questionDate = new ArrayList<>
                (Arrays.asList(
                        new Question("Кой е най-високият връх в България?", "Ботев", "Мусала", "Българка", "Черни Връх", "Geography", "Мусала"),
                        new Question("Кой е втория по голимина град в България?", "Пловдив", "София", "В. Търново", "Варна", "Geography", "Пловдив"),
                        new Question("Коя е най-дългата река в България?", "Янтра", "Искър", "Марица", "Лом", "Geography", "Искър"),
                        new Question("С коя държава не граничи България?", "Турция", "Гърция", "Сърбия", "Испания", "Geography", "Испания"),
                        new Question("Коя е столицата на България?", "София", "Пловдив", "Варна", "Бургас", "Geography", "София"),
                        new Question("До колко точки се играе волейболен мач?", "25", "22", "23", "26", "Sport", "25"),
                        new Question("Колко време трае един футболен мач?", "60 минути", "75 минути", "90 минути", "120 минути", "Sport", "90 минути"),
                        new Question("От колко играчи на полето се състои един баскетболен отбор?", "4", "6", "5", "7", "Sport", "5"),
                        new Question("Колко е времетраенето на един баскетболен мач?", "40 минути", "50 минути", "20 минути", "30 минути", "Sport", "40 минути"),
                        new Question("Кое от посочените не е позиция във футбола?", "Защитник", "Нападател", "Вратар", "Разпределител", "Sport", "Разпределител")
                ));

        return  questionDate;
    }

    public String[] getCategories(){

        String[] categories = new String[]{"None","Sport", "Geography"};
        return categories;
    }
}
