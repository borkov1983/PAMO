package com.example.bmi_app.model

import java.util.*

class QuestionItem(
    var question: String,
    picture: Int,
    correctAn: String,
    wrongAn1: String,
    wrongAn2: String,
    wrongAn3: String
) : Cloneable {

    var quizPicture:Int = picture
    var correctAnswer:String = correctAn
    var correctAnswerId:Int
    var answers = arrayListOf<String>()

    init {
        this.answers = arrayListOf<String>()
        this.correctAnswerId = 0
        this.answers.add(correctAn)
        this.answers.add(wrongAn1)
        this.answers.add(wrongAn2)
        this.answers.add(wrongAn3)
    }

    fun getQuest():String{
        return question
    }

    fun getCorrectAn():String {
        return answers[correctAnswerId]
    }

    fun getPhoto():Int{
    return quizPicture
}

    fun getAnswerById(id:Int):String{
        return this.answers.get(id)
    }

    fun ShuffleMyAnswersForDisplay(){
        answers.shuffle()
        correctAnswerId = answers.indexOf(correctAnswer)
    }

    @Throws(CloneNotSupportedException::class)
    public override fun clone(): QuestionItem {

        var clone:QuestionItem? = null
        try
        {
            clone = super.clone() as QuestionItem
        }
        catch (e: CloneNotSupportedException )
        {
            throw RuntimeException(e)
        }
        return clone
    }

}