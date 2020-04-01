package com.example.bmi_app.model;

import com.example.bmi_app.R;
import java.util.ArrayList;

public class QuestionDatabase {
    private ArrayList<QuestionItem> questions;

    public QuestionDatabase (){
        this.questions = new ArrayList<>();
        addQuestionToDatabase();
    }

    public int getQuestionCount() { return questions.size()-1; } // one fake question
    public QuestionItem getQuestionCloneById(int id) throws CloneNotSupportedException {
        return questions.get(id-1).clone();
    }

    public int getDatabaseSize(){
        return questions.size();
    }

    private void addQuestionToDatabase(){

        questions.add(new QuestionItem(
                "Epidemia koronawirusa określanego obecnie jako SARS-CoV-2 rozpoczęła się w mieście... ",
                R.drawable.foto1,
                "Wuhan",
                "Pierdziszewko",
                "Pompeje",
                "Sosnowiec"
        ));
        questions.add(new QuestionItem(
                "Kto sprawił, że koronowirus musiał udać się na 2-tygodniową kwarantannę ? ",
                R.drawable.foto2,
                "Chuck Norris",
                "Stalin",
                "Kubuś Puchatek",
                "Pan Tadeusz"
        ));

        questions.add(new QuestionItem(
                "Czy antybiotyki są skuteczne w zapobieganiu lub leczeniu COVID-19? ",
                R.drawable.foto3,
                "NIE",
                "TAK",
                "TAK, z alkoholem",
                "W zależności "
        ));
        questions.add(new QuestionItem(
                "Święty Graal czasów zarazy, epidemii koronawirusa to ... ",
                R.drawable.foto4,
                "papier toaletowy",
                "aparat na zęby",
                "buty sportowe",
                "bilet na koncert"
        ));
        questions.add(new QuestionItem(
                "Objawem choroby koronowirusa jest... ",
                R.drawable.foto5,
                "gorączka",
                "wrastający paznokieć",
                "swędzenie włosów",
                "swędzenie zębów"
        ));
        questions.add(new QuestionItem(
                "Czy alkohol zwalcza koronawirusa? ",
                R.drawable.foto6,
                "Nie zaszkodzi",
                "tylko drogi",
                "nie",
                "tylko tani"
        ));
        questions.add(new QuestionItem(
                "Ile trwa kwarantanna? ",
                R.drawable.foto7,
                "14 dni",
                "aż się alkohol      skończy",
                "3,5 godziny",
                "tyle co nic"
        ));

        questions.add(new QuestionItem(
                "Empty ",
                R.drawable.foto1,
                "Empty",
                "Empty",
                "Empty",
                "Empty"
        ));


    }


}
