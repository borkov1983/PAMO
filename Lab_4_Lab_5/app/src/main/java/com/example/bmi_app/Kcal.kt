package com.example.bmi_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.kcal.*
import kotlin.math.roundToInt

class Kcal : AppCompatActivity() {

    var sex=""
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kcal)

        home_button.setOnClickListener {
            val intent = Intent (this, Bmi::class.java)
            startActivity(intent) }

        calories.setOnClickListener {
            showCalories()
        }
    }

    fun showCalories(){
        val inputWeight = weight_calories.text.toString()
        val inputHeight = height_calories.text.toString()
        val inputAge = age.text.toString()

        val selectedId  = radioSexGroup.checkedRadioButtonId
        sex = resources.getResourceEntryName(selectedId).toString()

        if (!inputHeight.isEmpty() && !inputWeight.isEmpty() && !inputAge.isEmpty()) {

            val weightValue = inputWeight.toFloat()
            val heightValue = inputHeight.toFloat()
            val ageValue = inputAge.toInt()

            calculateCalories(sex, weightValue, heightValue, ageValue)
        }
    }

    @SuppressLint("SetTextI18n")
    fun calculateCalories(sex:String, weight:Float, height:Float, age:Int){

        val result:Double
        result = if(sex.equals("maleButton")){
            66.47+13.7*weight+5.0*height-6.76*age;
        }else{
            655.1+9.567*weight+1.85*height-4.68*age;
        }
        result_calories.setText(result.roundToInt().toString() + " Kcal")
    }


}