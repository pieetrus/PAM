package com.example.lab_a4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class loginActivity extends AppCompatActivity {

    private TextView tvResponse;
    private EditText etLogin;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        tvResponse = findViewById(R.id.tvLoginResponse);
        etLogin = findViewById(R.id.etLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLogin.getText().toString();
                if (login.toLowerCase().contains("marek")){
                    tvResponse.setText("Hej Panie doktorze!");
                } else{
                    tvResponse.setText("Hej " + login + " !");
                }
                final Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("login", login);
                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                }, 1000);

            }
        });

    }
}
