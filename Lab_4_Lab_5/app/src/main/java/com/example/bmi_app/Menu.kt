package com.example.bmi_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.menu.*

class Menu: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        home_button.setOnClickListener {
            val intent = Intent (this, Bmi::class.java)
            startActivity(intent)
            }
        displayDiet(Bmi.bmiValueString)
    }

    fun displayDiet(data:String){

        when (data){
            "Wygłodzenie" -> {what_eat.setText(R.string.wyglodzenie).toString()
                obraz.setImageResource(R.drawable.wyglodzenie)}
            "Wychudzenie" -> {what_eat.setText(R.string.wychudzenie).toString()
                obraz.setImageResource(R.drawable.wychudzenie)}
            "Niedowaga" ->{what_eat.setText(R.string.wyglodzenie).toString()
                obraz.setImageResource(R.drawable.niedowaga)}
            "Waga prawidłowa" ->{what_eat.setText(R.string.prawidlowa).toString()
                obraz.setImageResource(R.drawable.wagok)}
            "Nadwaga" -> {what_eat.setText(R.string.nadwaga).toString()
                obraz.setImageResource(R.drawable.nadwaga)}
            "Otyłość I stopnia" -> {what_eat.setText(R.string.otyloscjeden).toString()
                obraz.setImageResource(R.drawable.otylosc1)}
            "Otyłość II stopnia" -> {what_eat.setText(R.string.otyloscdwa).toString()
                obraz.setImageResource(R.drawable.otylosc2)}
            "Otyłość III stopnia" ->{what_eat.setText(R.string.otylosctrzy).toString()
                obraz.setImageResource(R.drawable.otylosc3)}
        }

    }
}
