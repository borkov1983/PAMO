package com.example.bmi_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anychart.core.Chart

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gotobmi.setOnClickListener(){
            val intent = Intent(this, Bmi::class.java)
            startActivity(intent)
        }

        gotoquiz.setOnClickListener{
            val intent = Intent(this, Quiz::class.java)
            startActivity(intent)
        }

        gotochart.setOnClickListener{
            val intent = Intent(this, com.example.bmi_app.Chart::class.java)
            startActivity(intent)
        }
    }
}

