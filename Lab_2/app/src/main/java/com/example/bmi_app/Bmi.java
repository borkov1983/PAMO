package com.example.bmi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bmi extends AppCompatActivity {
    private EditText weight;
    private EditText height;
    private TextView bmi;
    private TextView result;
    private Button calculate;
    private String description = "";
    private String BMI;
    static String bmiValueString;

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        Button backHome = findViewById(R.id.home_button);
        Button goToKcal = findViewById(R.id.kcal_button);
        Button goToMenu = findViewById(R.id.menu_button);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
        goToKcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Kcal.class);
                startActivity(intent);
            }
        });
        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                if(BMI==null || BMI.isEmpty()){
                    result.setText(R.string.bmiNull);
                }else
                    startActivity(intent);
            }
        });

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        bmi = findViewById(R.id.bmi);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();

            }
        });
    }

    public String calculateBMI(){
        String inputWeight = weight.getText().toString();
        String inputHeight = height.getText().toString();

        if(inputWeight.isEmpty() || inputHeight.isEmpty() || inputWeight.equals("0") || inputHeight.equals("0")){
            description = "Uzupełnij poprawnie dane";
            result.setText(description);
        }else {

            double weightValue = Double.parseDouble(inputWeight);
            double heightValue = Double.parseDouble(inputHeight) / 100;

            double bmiValue = (double) (Math.round((weightValue / (Math.pow(heightValue, 2))) * 100)) / 100;


            bmiValueString = Double.toString(bmiValue);

            bmi.setText(bmiValueString);

            if (bmiValue < 16) {
                bmiValueString = "Wygłodzenie";
            } else if (bmiValue >= 16 && bmiValue < 17) {
                bmiValueString = "Wychudzenie";
            } else if (bmiValue >= 17 && bmiValue < 18.5) {
                bmiValueString = "Niedowaga";
            } else if (bmiValue >= 18.5 && bmiValue < 25) {
                bmiValueString = "Waga prawidłowa";
            } else if (bmiValue >= 25 && bmiValue < 30) {
                bmiValueString = "Nadwaga";
            } else if (bmiValue >= 30 && bmiValue < 35) {
                bmiValueString = "Otyłość I stopnia";
            } else if (bmiValue >= 35 && bmiValue < 40) {
                bmiValueString = "Otyłość II stopnia";
            } else {
                bmiValueString = "Otyłość III stopnia";
            }
            result.setText(bmiValueString);
            BMI = bmiValueString;
            setBMI(BMI);

        }
        return bmiValueString;
    }

}
