package com.example.lab_a4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class indexActivity extends AppCompatActivity {

    private TextView indexNr;
    private TextView tvResponse;
    private RatingBar rbReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        indexNr = findViewById(R.id.tvIndex);
        tvResponse = findViewById(R.id.tvResponse);
        rbReview = findViewById(R.id.rbReview);

        Intent intent = getIntent();
        String s1 = intent.getStringExtra("value");

        if (s1.equals("241174")){
            indexNr.setText("Jakub Pietrus: "+s1);
        }
        else {
            indexNr.setText("Niestety nie wiem kto to jest. Ale możesz ocenić! "+s1);
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

    }
}
