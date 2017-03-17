package com.mad.inclass2a;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        final TextView outputBmi = (TextView) findViewById(R.id.outputBmi);
        final TextView outputStatus = (TextView) findViewById(R.id.outputStatus);

        Button btnCalculateBmi = (Button) findViewById(R.id.buttonBmiCalculate);

        btnCalculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText weight = (EditText) findViewById(R.id.inputWeight);
                EditText heightFeet = (EditText) findViewById(R.id.inputHeightFeet);
                EditText heightInches = (EditText) findViewById(R.id.inputHeightInch);

                //Log.d("demo","enteredonClick");

                //Log.d("demo","height="+height);


                if(weight.getText().toString().equals("") || weight.getText().toString().equals(".") ||
                        heightFeet.getText().toString().equals("") || heightFeet.getText().toString().equals(".") ||
                        heightInches.getText().toString().equals("") || heightInches.getText().toString().equals(".")){
                    Context context = getApplicationContext();
                    CharSequence text = getResources().getString(R.string.invalid_input);
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context,text,duration).show();
                }

                else {
                    float wt = Float.parseFloat(weight.getText().toString().trim());
                    int htFeet = Integer.parseInt(heightFeet.getText().toString().trim());
                    int htInches = Integer.parseInt(heightInches.getText().toString().trim());
                    int height = (htFeet*12) + htInches;
                    float bmiIndex = (wt * 703) / (height*height);
                    double roundOff = Math.round(bmiIndex * 100.0) / 100.0;

                    //Log.d("demo","Bmi="+bmiIndex);
                    outputBmi.setText(getResources().getString(R.string.your_bmi) + roundOff);
                    //Log.d("demo","set text ur bmi");

                    if (bmiIndex <= 18.5) {
                        outputStatus.setText(getString(R.string.you_are_underweight));
                        Log.d("demo", "under");
                    } else if (bmiIndex > 18.5 && bmiIndex <= 24.9) {
                        outputStatus.setText(getString(R.string.you_are_normalweight));
                        Log.d("demo", "normal");
                    } else if (bmiIndex >= 25 && bmiIndex <= 29.9) {
                        outputStatus.setText(getString(R.string.you_are_overweight));
                        Log.d("demo", "over");
                    } else if (bmiIndex >= 30) {
                        outputStatus.setText(getString(R.string.you_are_obese));
                        Log.d("demo", "obese");
                    }

                    Context context = getApplicationContext();
                    CharSequence text = getResources().getString(R.string.bmi_calculated);
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context,text,duration).show();
                }
            }
        });
    }
}
