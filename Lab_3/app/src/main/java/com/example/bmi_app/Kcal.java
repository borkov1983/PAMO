package com.example.bmi_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Kcal extends AppCompatActivity {
    Button calculate_calories;
    TextView result_calories;
    EditText weight_calories;
    EditText height_calories;
    EditText age_calories;
    RadioGroup sex_group;
    RadioButton radioSexButton;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kcal);
        Button backBmi = findViewById(R.id.home_button);
        backBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bmi.class);
                startActivity(intent);
            }
        });

        calculate_calories = findViewById(R.id.calories);
        sex_group = findViewById(R.id.radioSexGroup);
        result_calories = findViewById(R.id.result_calories);
        weight_calories = findViewById(R.id.weight_calories);
        height_calories = findViewById(R.id.height_calories);
        age_calories = findViewById(R.id.age);

        calculate_calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalories();
            }
        });

    }

    public void showCalories() {

        String inputWeight = weight_calories.getText().toString();
        String inputHeight = height_calories.getText().toString();
        String inputAge = age_calories.getText().toString();

        int selectedId = sex_group.getCheckedRadioButtonId();
        radioSexButton = findViewById(selectedId);
        sex = radioSexButton.getText().toString();

        if (!inputHeight.isEmpty() && !inputWeight.isEmpty() && !inputAge.isEmpty()) {
            float heightValue = Float.parseFloat(inputHeight);
            float weightValue = Float.parseFloat(inputWeight);
            int ageValue = Integer.parseInt(inputAge);

            calculateCalories(sex, weightValue, heightValue, ageValue);

        }
    }
    public void calculateCalories(String sex, float weight, float height, int age){
        double result;
        if(sex.equals("Mężczyzna")){
            result= 66.47+13.7*weight+5.0*height-6.76*age;
        }else{
            result= 655.1+9.567*weight+1.85*height-4.68*age;
        }
        result_calories.setText(String.valueOf(Math.round(result)));
    }
}
