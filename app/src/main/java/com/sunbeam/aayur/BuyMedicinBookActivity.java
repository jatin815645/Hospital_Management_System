package com.sunbeam.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class BuyMedicinBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicin_book);


        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContact);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        Log.d("TAG", "onCreate: price - "+price);
        String date = intent.getStringExtra("date");
//        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                if (price != null) {
                    if (price != null) { // Ensure the split result contains two parts
//                        String time = intent.getStringExtra("time");

                        try {
                            float parsedPrice = Float.parseFloat(price);
                            int parsedPincode = Integer.parseInt(edpincode.getText().toString());

                            Database db = new Database(getApplicationContext(), "healthCare", null, 1);
                            db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), parsedPincode, date, "", parsedPrice, "medicine");
                            db.removeCart(username, "medicine");
                            Toast.makeText(BuyMedicinBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(BuyMedicinBookActivity.this, HomeActivity.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuyMedicinBookActivity.this, "Invalid price or pincode format", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(BuyMedicinBookActivity.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BuyMedicinBookActivity.this, "Price not provided", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}