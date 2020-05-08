package com.example.lab_a4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class indexActivity extends AppCompatActivity {

    private TextView indexNr;
    private TextView tvResponse;
    private RatingBar rbReview;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        indexNr = findViewById(R.id.tvIndex);
        tvResponse = findViewById(R.id.tvResponse);
        rbReview = findViewById(R.id.rbReview);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        final String index = intent.getStringExtra("index");
        final String login = intent.getStringExtra("login");

        if (index.equals("241174")){
            indexNr.setText("Jakub Pietrus: " + index);
        }
        else {
            indexNr.setText("Niestety nie wiem kto to jest. Ale możesz ocenić! "+index);
        }

        rbReview.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <=2){
                    tvResponse.setText("Kuuuurcze lipa. Od razu biorę się za poprawę.");
                }
                else if (rating <= 4){
                    tvResponse.setText("Uffff. Ważne że zaliczone.");
                } else if (rating <= 5.5){
                    tvResponse.setText("Yaaaaaaaaaaaaaaaaaaaaaaay.");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });

    }
}
