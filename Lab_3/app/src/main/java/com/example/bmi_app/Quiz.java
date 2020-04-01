package com.example.bmi_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bmi_app.model.QuestionDatabase;
import com.example.bmi_app.model.QuestionItem;

public class Quiz extends AppCompatActivity {
    private Button backToMainButton, nextQuestionButton, answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    private Button[] answerButtons;
    private TextView questionView, quizTitleView, scoreView;
    private ImageView questionImage;
    private int score, questionId;
    private QuestionDatabase questions;
    private QuestionItem selectedQuestion;
    private Boolean answeredCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        answer1Btn = findViewById(R.id.button);
        answer2Btn = findViewById(R.id.button2);
        answer3Btn = findViewById(R.id.button3);
        answer4Btn = findViewById(R.id.button4);
        answerButtons = new Button[] {answer1Btn, answer2Btn, answer3Btn, answer4Btn};
        questionView = findViewById(R.id.qestion_view);
        quizTitleView = findViewById(R.id.quiz_title);
        scoreView = findViewById(R.id.quiz_title);
        questionImage = findViewById(R.id.quiz_image);
        nextQuestionButton = findViewById(R.id.button_next);
        backToMainButton = findViewById(R.id.back);

        questionId = 1;
        score = 0;

        answeredCorrect = false;

        try{
            questions = new QuestionDatabase();

            nextQuestion();
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage());
        }

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
        nextQuestionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    score = 0;
                    nextQuestion();
                }
                catch (CloneNotSupportedException e){
                    Log.e("Next question fail", e.getMessage());
                }
            }
        });
        answer1Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer1Btn);

                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail on next step", e.getMessage());
                }
            }
        });
        answer2Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer2Btn);

                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail after assess score", e.getMessage());
                }

            }
        });
        answer3Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer3Btn);

                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail after assess score", e.getMessage());
                }
            }
        });
        answer4Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer4Btn);

                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail after assess score", e.getMessage());
                }
            }
        });

    }

    private void nextQuestion() throws CloneNotSupportedException {
        int size = questions.getDatabaseSize();

        try{
            selectedQuestion = questions.getQuestionCloneById(questionId);
            selectedQuestion.ShuffleMyAnswersForDisplay();
        }
        catch (CloneNotSupportedException e){
            Log.e("Cloning question failed", e.getMessage());
        }

        Drawable newPicture = getResources().getDrawable(selectedQuestion.getPhoto());
        questionImage.setImageDrawable(newPicture);

        setDefaultButtonBackgroundToAllAnswersBtns();
        questionView.setText(selectedQuestion.getQuestion());
        answer1Btn.setText(selectedQuestion.getAnswerById(0));
        answer2Btn.setText(selectedQuestion.getAnswerById(1));
        answer3Btn.setText(selectedQuestion.getAnswerById(2));
        answer4Btn.setText(selectedQuestion.getAnswerById(3));
        scoreView.setText("Score: " + score +" / 7");

        displayAllQuizRelatedItems();

        if(questionId <= size-1 ){
            questionId ++;
        }
        else{
            questionId = 1;
            hideAllQuizRelatedItems();
        }

    }

    private void setDefaultButtonBackgroundToAllAnswersBtns(){
        for (Button button: answerButtons) {
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void CheckSelectedAnswer(Button selectedBtn){
        String answer = selectedBtn.getText().toString();

        if(selectedQuestion.getCorrectAnswer() == answer){
            answeredCorrect = true;
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.correct_answer));
            makeAnswerAssessmentToast(answeredCorrect);
            score = answeredCorrect ? ++score : score;

        }
        else{
            answeredCorrect = false;
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.incorrect_answer));
            makeAnswerAssessmentToast(answeredCorrect);
        }
    }

    private void makeAnswerAssessmentToast(Boolean answerStatus){
        String text = answerStatus ? "Dobrze" : "Błąd";
        int toastBg = answerStatus ? getResources().getColor(R.color.correct_answer) : getResources().getColor(R.color.incorrect_answer);

        Toast myToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 180);
        View view = myToast.getView();
        view.setBackgroundColor(toastBg);
        myToast.show();
    }


    private void hideAllQuizRelatedItems(){
        quizTitleView.setVisibility(View.VISIBLE);
        questionImage.setVisibility(View.INVISIBLE);
        questionView.setVisibility(View.INVISIBLE);
        answer1Btn.setVisibility(View.INVISIBLE);
        answer2Btn.setVisibility(View.INVISIBLE);
        answer3Btn.setVisibility(View.INVISIBLE);
        answer4Btn.setVisibility(View.INVISIBLE);
        nextQuestionButton.setVisibility(View.INVISIBLE);
        backToMainButton.setVisibility(View.VISIBLE);
    }

    private void displayAllQuizRelatedItems(){
        quizTitleView.setVisibility(View.VISIBLE);
        questionImage.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
        answer1Btn.setVisibility(View.VISIBLE);
        answer2Btn.setVisibility(View.VISIBLE);
        answer3Btn.setVisibility(View.VISIBLE);
        answer4Btn.setVisibility(View.VISIBLE);
        nextQuestionButton.setVisibility(View.INVISIBLE);
        backToMainButton.setVisibility(View.INVISIBLE);
    }

}