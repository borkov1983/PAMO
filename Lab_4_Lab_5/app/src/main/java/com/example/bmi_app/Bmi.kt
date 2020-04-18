package com.example.bmi_app

import androidx.appcompat.app.AppCompatActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import kotlinx.android.synthetic.main.bmi.*
import java.lang.String.format
import kotlin.math.pow

class Bmi : AppCompatActivity() {
    companion object {
        var bmiValueString = ""
    }
    var BMI = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi)

        calculateBMI()

        home_button.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        kcal_button.setOnClickListener {
            val intent = Intent (this, com.example.bmi_app.Kcal::class.java)
            startActivity(intent)
        }

        menu_button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            if(BMI.isEmpty()){
                result.text = "Najpierw Oblicz BMI"
            }else
            startActivity(intent)
        }
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun calculateBMI():String{
//        val inputWeight = weight.text.toString()
//        val inputHeight = height.text.toString()

        calculate.setOnClickListener {
            val inputWeight = weight.text.toString()
            val inputHeight = height.text.toString()
            if(inputWeight.isEmpty() || inputHeight.isEmpty() || inputHeight.equals("0") || inputWeight.equals("0")){
                result.text = "Uzupełnij poprawnie dane"
            }else{
                val weightValue = inputWeight.toDouble()
                val heightValue = inputHeight.toDouble()
                val bmiValue = format("%.1f",((weightValue/((heightValue.pow(2))/100))*100)).toDouble()

                bmiValueString = bmiValue.toString()

                bmi.setText(bmiValueString)

                bmiValueString = when (bmiValue){
                    in 0.0..15.9 -> "Wygłodzenie"
                    in 16.0..16.9 -> "Wychudzenie"
                    in 17.0..18.4 -> "Niedowaga"
                    in 18.5..24.9 -> "Waga prawidłowa"
                    in 25.0..29.9 -> "Nadwaga"
                    in 30.0..34.9 -> "Otyłość I stopnia"
                    in 35.0..40.0 -> "Otyłość II stopnia"
                    in 40.0..100.0 -> "Otyłość III stopnia"
                    else -> "Błędne dane"
                }
                result.text = bmiValueString
                BMI = bmiValueString

            }
        }
        return bmiValueString
    }
}
