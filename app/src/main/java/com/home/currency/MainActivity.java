package com.home.currency;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edWeight;
    private EditText edHeight;
    private TextView result;
    private EditText edNTD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_linear); //for exercise

        //for Homework 2
        setContentView(R.layout.activity_main);
        findViews();
    }

    // for Homework 2
    public void go(View view) {
        String sNtd = edNTD.getText().toString().trim();
        String sTitle = "Problem";
        String sMsg = "Please enter your NTD amount !! ";

        if (!sNtd.equals("")) {
            float exUS = 30.9f;
            try {
                float rtValue = Float.parseFloat(sNtd) / exUS;

                if (rtValue < 1) {
                    sTitle = "Problem";
                    sMsg = "USD cannot < 1 ";
                } else {
                    sMsg = "USD is " + rtValue;
                    sTitle = "Result";
                }
            } catch (Exception e) {
                sMsg = "NTD is not decimal !";
            }

            if (sNtd.startsWith(".") || sNtd.startsWith("0.")) {
                sTitle = "Problem";
                sMsg = "NTD cannot < 1 ";
            }
        }

        new AlertDialog.Builder(this)
                .setTitle(sTitle)
                .setMessage(sMsg)
                .setPositiveButton("OK", null)
                .show();
    }

    // for exercise
    private void findViews() {
        // for Homework 2
        edNTD = findViewById(R.id.ntd);
        Button btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go(view);
            }
        });

        // for exercise
        /*edWeight = findViewById(R.id.edWeight);
        edHeight = findViewById(R.id.edHeight);
        result = findViewById(R.id.txtResult);
        Button help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Help")
                        .setMessage("The body mass index (BMI) or Quetelet index is a value derived from the mass (weight) and height of an individual.")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });*/
    }

    public void bmi(View view) {

        String w = edWeight.getText().toString();
        String h = edHeight.getText().toString();
        float weight = Float.parseFloat(w);
        float height = Float.parseFloat(h);
        float bmi = weight / (height * height);
        Log.d("MainActivity","BMI:" + bmi);

        Toast.makeText(this, "Your BMI is " + bmi, Toast.LENGTH_LONG).show();
        result.setText("Your BMI is " + bmi);

        new AlertDialog.Builder(this)
                .setTitle("BMI")
                .setMessage("Your BMI is " + bmi)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        edHeight.setText("");
                        edWeight.setText("");
                    }
                })
                .show();
    }
}
