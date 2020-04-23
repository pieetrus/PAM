package com.example.lab_a2_tip_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seek_bar_tip;
    private EditText et_base;
    private EditText et_tip_percentage;
    private TextView tv_tip_percentage;
    private TextView tv_tip_amount;
    private TextView tv_total_amount;
    private TextView tv_review;
    private RatingBar rb_review;
    private TextView tv_review_message;


    private Button btn_back;
    private int tip_percentage;
    private double base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();

        seek_bar_tip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_tip_percentage.setText(progress + "%");
                et_tip_percentage.setText(""+progress);
                tip_percentage = progress;
                compute_total();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        et_base.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String base_text = et_base.getText().toString();
                if (!base_text.matches("")){
                    base = Double.parseDouble(base_text);
                }
                else{
                    tv_total_amount.setText("");
                    base = 0;
                }
                compute_total();
            }
        });

        et_tip_percentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tip_text = et_tip_percentage.getText().toString();
                if (tip_text.matches("")){
                    tip_percentage = 0;
                    tv_tip_percentage.setText("");
                    seek_bar_tip.setProgress(0);
                }
                else{
                    tip_percentage = Integer.parseInt(tip_text);
                    tv_tip_percentage.setText(tip_percentage + "%");
                    seek_bar_tip.setProgress(tip_percentage);
                }
                compute_total();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seek_bar_tip.setVisibility(View.VISIBLE);
                tv_tip_percentage.setVisibility(View.VISIBLE);
                btn_back.setVisibility(View.GONE);
                et_tip_percentage.setVisibility(View.GONE);
            }
        });

        rb_review.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 2){
                    tv_review_message.setVisibility(View.VISIBLE);
                    tv_review_message.setText("Hmm chyba nie powinieneś dawać zbyt wielkiego napiwku jak za taką jakość obsługi...");
                }
                if (rating > 2 && rating <= 3){
                    tv_review_message.setVisibility(View.VISIBLE);
                    tv_review_message.setText("Może masz przy sobie jakieś drobne?");
                }
                if (rating > 3 && rating <= 4){
                    tv_review_message.setVisibility(View.VISIBLE);
                    tv_review_message.setText("Nie było źle. Warto coś dorzucić!");
                }
                if (rating > 4 && rating <= 5){
                    tv_review_message.setVisibility(View.VISIBLE);
                    tv_review_message.setText("Za takie starania trzeba wynagrodzić! ;)");
                }
            }
        });
    }


    private void setupUIViews(){
        seek_bar_tip = findViewById(R.id.seek_bar_tip);
        et_base = findViewById(R.id.et_base);
        tv_tip_percentage = findViewById(R.id.tv_tip_precentage);
        tv_tip_amount = findViewById(R.id.tv_tip_amount);
        tv_total_amount = findViewById(R.id.tv_total_amount);
        et_tip_percentage = findViewById(R.id.et_tip_percentage);
        btn_back = findViewById(R.id.btn_back);
        tv_review = findViewById(R.id.tv_review_ask);
        rb_review = findViewById(R.id.rb_review);
        tv_review_message = findViewById(R.id.tv_review_message);

        seek_bar_tip.setProgress(15);
        tv_tip_percentage.setText("15%");
        tip_percentage = 15;
    }

    private void compute_total(){
        double tip_amount = base * tip_percentage/100;
        double total = base + tip_amount;
        tv_tip_amount.setText(String.format("%.2f", tip_amount));
        tv_total_amount.setText(String.format("%.2f", total));
    }


    public void change_tip_percentage(View view) {
        seek_bar_tip.setVisibility(View.GONE);
        tv_tip_percentage.setVisibility(View.GONE);
        btn_back.setVisibility(View.VISIBLE);
        et_tip_percentage.setVisibility(View.VISIBLE);
    }

}
