package com.example.bmi_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    private TextView text;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button backBmi = findViewById(R.id.home_button);
        backBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bmi.class);
                startActivity(intent);

            }
        });

        text = findViewById(R.id.what_eat);
        imageView= findViewById(R.id.obraz);
        displayDiet(Bmi.bmiValueString);
    }

    private void displayDiet(String data){

        switch(data) {
            case "Wygłodzenie":
                text.setText(R.string.wyglodzenie);
                imageView.setImageResource(R.drawable.wyglodzenie);
                break;

            case "Wychudzenie":
                text.setText(R.string.wychudzenie);
                imageView.setImageResource(R.drawable.wyglodzenie);
                break;
            case "Niedowaga":
                text.setText(R.string.niedowaga);
                imageView.setImageResource(R.drawable.niedowaga);
                break;

            case "Waga prawidłowa":
                text.setText(R.string.prawidlowa);
                imageView.setImageResource(R.drawable.wagok);
                break;
            case "Nadwaga":
                text.setText(R.string.nadwaga);
                imageView.setImageResource(R.drawable.nadwaga);
                break;
            case "Otyłość I stopnia":
                text.setText(R.string.otyloscjeden);
                imageView.setImageResource(R.drawable.otylosc1);
                break;
            case "Otyłość II stopnia":
                text.setText(R.string.otyloscdwa);
                imageView.setImageResource(R.drawable.otylosc2);
                break;
            case "Otyłość III stopnia":
                text.setText(R.string.otylosctrzy);
                imageView.setImageResource(R.drawable.otylosc3);
                break;
        }
    }
}
