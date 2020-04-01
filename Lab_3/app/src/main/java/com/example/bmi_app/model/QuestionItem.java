package com.example.bmi_app.model;


import java.util.ArrayList;
import java.util.Collections;

public class QuestionItem implements Cloneable{
    private String question;
    private int quizPicture;
    private String correctAnswer;
    private int correctAnswerId;
    private ArrayList <String> answers;

    public QuestionItem(
            String question,
            int picture,
            String correctAn,
            String wrongAn1,
            String wrongAn2,
            String wrongAn3)
    {
        this.answers = new ArrayList<>();
        this.question = question;
        this.quizPicture = picture;
        this.correctAnswer = correctAn;
        this.correctAnswerId = 0;
        this.answers.add(correctAn);
        this.answers.add(wrongAn1);
        this.answers.add(wrongAn2);
        this.answers.add(wrongAn3);
    }

    public String getQuestion() {
        return question;
    }
    public String getCorrectAnswer() {
        return answers.get(correctAnswerId);
    }
    public int getPhoto() { return quizPicture; }
    public String getAnswerById(int id){ return this.answers.get(id); }
    public void ShuffleMyAnswersForDisplay() {
        Collections.shuffle(answers);
        correctAnswerId = answers.indexOf(correctAnswer);
    }


    @Override
    protected QuestionItem clone() throws CloneNotSupportedException {
        QuestionItem clone = null;
        try
        {
            clone = (QuestionItem) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
        return clone;
    }


}
