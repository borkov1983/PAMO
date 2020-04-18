package com.example.bmi_app

import android.annotation.SuppressLint

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmi_app.model.QuestionDatabase
import com.example.bmi_app.model.QuestionItem
import kotlinx.android.synthetic.main.quiz.*

class Quiz : AppCompatActivity() {

    var score:Int = 0
    var questionId:Int = 1
    var answeredCorrect:Boolean=false
    lateinit var questions:QuestionDatabase
    lateinit var selectedQuestion: QuestionItem
    lateinit var answerButtons:Array<Button>
    lateinit var questionImage:ImageView
    lateinit var quizTitleView:TextView
    lateinit var scoreView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)
        answerButtons =
            arrayOf(button, button2, button3, button4)
        score = 0
        questionId = 1
        answeredCorrect = false
        quizTitleView = findViewById(R.id.quiz_title)
        scoreView = findViewById(R.id.quiz_title)
        questionImage = findViewById(R.id.quiz_image)

        try{
            questions = QuestionDatabase()
            nextQuestion()
        }
        catch(ex:Exception){
            Log.e("Error", ex.message);
        }

        back.setOnClickListener{
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        button_next.setOnClickListener{
            try{
                score = 0;
                nextQuestion()
            }
            catch (e:CloneNotSupportedException){
                Log.e("Next question fail", e.message);
            }
        }

        button.setOnClickListener{
            setDefaultButtonBackgroundToAllAnswersBtns()
            CheckSelectedAnswer(button)

            try{
                nextQuestion()
            }
            catch (e:Exception){
                Log.e("Fail on next step", e.message)
            }
        }

        button2.setOnClickListener{
            setDefaultButtonBackgroundToAllAnswersBtns();
            CheckSelectedAnswer(button2);

            try{
                nextQuestion();
            } catch (e:Exception){
                Log.e("Fail after assess score", e.message);
            }
        }

        button3.setOnClickListener{
            setDefaultButtonBackgroundToAllAnswersBtns()
            CheckSelectedAnswer(button3)
            try{
                nextQuestion()
            }
            catch (e:Exception){
                Log.e("Fail after assess score", e.message);
            }
        }

        button4.setOnClickListener{
            setDefaultButtonBackgroundToAllAnswersBtns()
            CheckSelectedAnswer(button4)
            try{
                nextQuestion();
            }
            catch (e:Exception){
                Log.e("Fail after assess score", e.message);
            }
        }
    }
    @SuppressLint("SetTextI18n")
    @Throws(CloneNotSupportedException::class)

    fun nextQuestion(){

        val size:Int = questions.getDatabaseSize()

        try {
            selectedQuestion = questions.getQuestionCloneById(questionId)
            selectedQuestion.ShuffleMyAnswersForDisplay()
        }
        catch (e:CloneNotSupportedException){
            Log.e("Cloning question failed", e.message)
        }

        val newPicture: Drawable? = getDrawable(selectedQuestion.getPhoto())
        questionImage.setImageDrawable(newPicture)

        setDefaultButtonBackgroundToAllAnswersBtns()
        qestion_view.setText(selectedQuestion.getQuest())
        button.setText(selectedQuestion.getAnswerById(0))
        button2.setText(selectedQuestion.getAnswerById(1))
        button3.setText(selectedQuestion.getAnswerById(2))
        button4.setText(selectedQuestion.getAnswerById(3))
        scoreView.setText("Score: $score / 7")

        displayAllQuizRelatedItems()

        if (questionId <= size - 1) {
            questionId++
        } else {
            questionId = 1
            hideAllQuizRelatedItems()
        }
    }

    private fun setDefaultButtonBackgroundToAllAnswersBtns(){
        for (button in answerButtons) {
            button.setBackgroundColor(getColor(R.color.colorPrimary))
        }
    }

    private fun CheckSelectedAnswer(selectedBtn:Button){

        val answer:String = selectedBtn.text.toString()

        if(selectedQuestion.getCorrectAn() == answer){
            answeredCorrect = true
            selectedBtn.setBackgroundColor(getColor(R.color.correct_answer))
            makeAnswerAssessmentToast(answeredCorrect)
            score = if(answeredCorrect) ++score else score
        }
        else{
            answeredCorrect = false
            selectedBtn.setBackgroundColor(getColor(R.color.incorrect_answer))
            makeAnswerAssessmentToast(answeredCorrect)
        }
    }

    private fun makeAnswerAssessmentToast(answerStatus:Boolean){
        val text:String = if (answerStatus) "Dobrze" else "Błąd"
        val toastBg:Int = if(answerStatus) getColor(R.color.correct_answer) else getColor(R.color.incorrect_answer)
        val myToast:Toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        myToast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 180)
        val view:View = myToast.view
        view.setBackgroundColor(toastBg)
        myToast.show()

    }

    private fun hideAllQuizRelatedItems(){
        quizTitleView.visibility = View.VISIBLE
        questionImage.visibility = View.INVISIBLE
        qestion_view.visibility = View.INVISIBLE
        button.visibility = View.INVISIBLE
        button2.visibility = View.INVISIBLE
        button3.visibility = View.INVISIBLE
        button4.visibility = View.INVISIBLE
        button_next.visibility = View.INVISIBLE
        back.visibility = View.VISIBLE

    }

    private fun displayAllQuizRelatedItems(){
        quizTitleView.visibility = View.VISIBLE
        questionImage.visibility = View.VISIBLE
        qestion_view.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        button2.visibility = View.VISIBLE
        button3.visibility = View.VISIBLE
        button4.visibility = View.VISIBLE
        button_next.visibility= View.INVISIBLE
        back.visibility = View.INVISIBLE
    }
}