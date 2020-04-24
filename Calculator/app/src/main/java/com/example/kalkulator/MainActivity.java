package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button[] numbers;
    private Button add;
    private Button sub;
    private Button mul;
    private Button div;
    private Button equal;
    private Button clear;
    private TextView info;
    private TextView result;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char EQU = 0;
    private double val1 = Double.NaN;
    private double val2;
    private char ACTION;
    private static DecimalFormat df = new DecimalFormat("#.#");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();

        for (int i =0; i< 10; i++){
            final int finalI = i;
            numbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    info.setText(info.getText().toString() + finalI);
                }
            });

        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() >0) {
                    compute();
                    ACTION = ADDITION;
                    result.setText(val1 + "+");
                    info.setText(null);
                }
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() >0) {
                    compute();
                    ACTION = MULTIPLICATION;
                    result.setText(val1 + "*");
                    info.setText(null);
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() >0) {
                    compute();
                    ACTION = SUBTRACTION;
                    result.setText(val1 + "-");
                    info.setText(null);
                }
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() >0) {
                    compute();
                    ACTION = DIVISION;
                    result.setText(val1 + "/");
                    info.setText(null);
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() >0) {
                    compute();
                    ACTION = EQU;
                    if (val1 % 1 == 0){
                        result.setText(result.getText().toString() + val2 + "=" + df.format(val1));
                    }
                    else{
                        result.setText(result.getText().toString() + val2 + "=" + val1);
                    }
                    info.setText(null);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() > 0){
                    CharSequence name = info.getText().toString();
                    info.setText(name.subSequence(0,name.length()-1));
                }
                else{
                    val1 = Double.NaN;
                    val2 = Double.NaN;
                    info.setText(null);
                    result.setText(null);
                }
            }
        });
    }

    private void setupUIViews(){
        numbers = new Button[10];

        for (int i = 0; i < 10; i++){
            String btnID = "btn" + i;
            int id = getResources().getIdentifier(btnID, "id", getPackageName());
            numbers[i] = findViewById(id);
        }

        add = findViewById(R.id.btnAdd);
        sub = findViewById(R.id.btnSub);
        mul = findViewById(R.id.btnMul);
        div = findViewById(R.id.btnDiv);
        equal = findViewById(R.id.btnEqual);
        info = findViewById(R.id.tvControl);
        result = findViewById(R.id.tvResult);
        clear = findViewById(R.id.btnClear);
    }

    private void compute(){


        if (!Double.isNaN(val1)){
            val2 = Double.parseDouble(info.getText().toString());
            switch(ACTION){
                case ADDITION:
                    val1 += val2;
                    break;
                case SUBTRACTION:
                    val1 -= val2;
                    break;
                case MULTIPLICATION:
                    val1 *= val2;
                    break;
                case DIVISION:
                    val1 /= val2;
                    break;
                case EQU:
                    break;
            }
        }
        else{
            val1 = Double.parseDouble(info.getText().toString());
        }
    }

}
