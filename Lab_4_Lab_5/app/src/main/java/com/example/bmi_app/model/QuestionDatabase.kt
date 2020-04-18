package com.example.bmi_app.model

import com.example.bmi_app.R

class QuestionDatabase {
    var questions = ArrayList<QuestionItem>()

    constructor() {
        this.questions = java.util.ArrayList()
        addQuestionToDatabase()
    }

    @Throws(CloneNotSupportedException::class)
    fun getQuestionCloneById(id:Int):QuestionItem{
        return questions[id-1].clone()
    }

    fun getDatabaseSize():Int{
        return questions.size
    }

    fun addQuestionToDatabase(){

        questions.add( QuestionItem(
                "Epidemia koronawirusa określanego obecnie jako SARS-CoV-2 rozpoczęła się w mieście... ",
            R.drawable.foto1,
            "Wuhan",
            "Pierdziszewko",
            "Pompeje",
            "Sosnowiec"
        ))
        questions.add( QuestionItem(
                "Kto sprawił, że koronowirus musiał udać się na 2-tygodniową kwarantannę ? ",
            R.drawable.foto2,
            "Chuck Norris",
            "Stalin",
            "Kubuś Puchatek",
            "Pan Tadeusz"
        ))

        questions.add( QuestionItem(
                "Czy antybiotyki są skuteczne w zapobieganiu lub leczeniu COVID-19? ",
            R.drawable.foto3,
            "NIE",
            "TAK",
            "TAK, z alkoholem",
            "W zależności "
        ))
        questions.add( QuestionItem(
                "Święty Graal czasów zarazy, epidemii koronawirusa to ... ",
            R.drawable.foto4,
            "papier toaletowy",
            "aparat na zęby",
            "buty sportowe",
            "bilet na koncert"
        ))
        questions.add( QuestionItem(
                "Objawem choroby koronowirusa jest... ",
            R.drawable.foto5,
            "gorączka",
            "wrastający paznokieć",
            "swędzenie włosów",
            "swędzenie zębów"
        ))
        questions.add( QuestionItem(
                "Czy alkohol zwalcza koronawirusa? ",
            R.drawable.foto6,
            "Nie zaszkodzi",
            "tylko drogi",
            "nie",
            "tylko tani"
        ))
        questions.add( QuestionItem(
                "Ile trwa kwarantanna? ",
            R.drawable.foto7,
            "14 dni",
            "aż się alkohol      skończy",
            "3,5 godziny",
            "tyle co nic"
        ))

        questions.add( QuestionItem(
                "Empty ",
            R.drawable.foto1,
            "Empty",
            "Empty",
            "Empty",
            "Empty"
        ))

    }
}