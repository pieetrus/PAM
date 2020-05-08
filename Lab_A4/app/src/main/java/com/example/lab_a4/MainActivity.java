package com.example.lab_a4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button btnDial;
    private Button btnContacts;
    private Button btnMessageContact;
    private Button btnGeo;
    private Button btnClear;
    private Button btnStudent;
    private TextView tvContact;
    private TextView tvLogin;
    private EditText etPhoneNumber;
    private EditText etMessageContact;
    private EditText etMessageContent;
    private EditText etStudent;
    private EditText etLat;
    private EditText etLon;



    private final static int pickContactCode = 928; //parametr, może to być jakakolwiek liczba

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        Intent intent = getIntent();
        final String login = intent.getStringExtra("login");
        if (login.toLowerCase().contains("marek")){
            tvLogin.setText("Dr " + login);
        }else{
            tvLogin.setText(login);
        }

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "tel:" + etPhoneNumber.getText().toString();

                Intent intent = new Intent(Intent.ACTION_DIAL).setData(Uri.parse(phoneNumber));
                startActivity(intent);
            }
        });


        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactList();
            }
        });


        btnMessageContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = etMessageContact.getText().toString();
                final String stringSMS = "smsto:" + contact;
                String message = etMessageContent.getText().toString();
                Intent intent = new Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse(stringSMS));
                intent.putExtra("sms_body", message);
                startActivity(intent);
            }
        });

        btnGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat = etLat.getText().toString();
                String lon = etLon.getText().toString();
                if (lat.isEmpty() || lon.isEmpty())
                    return;
                openMap(lat, lon);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 etPhoneNumber.setText("");
                 etMessageContact.setText("");
                 etMessageContent.setText("");
                 etLat.setText("");
                 etLon.setText("");
                 etStudent.setText("");
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexNr = etStudent.getText().toString();
                Intent intent  = new Intent(getApplicationContext(), indexActivity.class);
                intent.putExtra("index", indexNr);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });

    }

    private void showContactList(){
        Intent contactListActivity = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactListActivity, pickContactCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case (pickContactCode):
                if (resultCode == Activity.RESULT_OK){
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()){
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        tvContact.setText(name);
                        etMessageContact.setText(name);
                    }
                }

        }
    }

    private void initialize(){
        btnDial = findViewById(R.id.btnDial);
        btnContacts = findViewById(R.id.btnContacts);
        btnMessageContact = findViewById(R.id.btnMessageContact);
        btnGeo = findViewById(R.id.btnGeo);
        btnClear = findViewById(R.id.btnClear);
        btnStudent = findViewById(R.id.btnStudent);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etMessageContent = findViewById(R.id.etMessageContent);
        tvContact = findViewById(R.id.tv_contact);
        tvLogin = findViewById(R.id.tvLogin);
        etMessageContact = findViewById(R.id.etMessageContact);
        etLat = findViewById(R.id.etLat);
        etLon = findViewById(R.id.etLon);
        etStudent = findViewById(R.id.etStudent);
    }

    private void openMap(String lat, String lon){
          String latitude = lat.replace(',', '.');
          String longitude = lon.replace(',', '.');

        String coordinates = "geo:" + latitude + ","+longitude;
        Intent mapActivity = new Intent(Intent.ACTION_VIEW);
        mapActivity.setData(Uri.parse(coordinates));
        startActivity(mapActivity);

    }

}
