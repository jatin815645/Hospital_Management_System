package com.sunbeam.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContact);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                String priceExtra = intent.getStringExtra("price");
                if (priceExtra != null) {
                    String[] price = priceExtra.split(java.util.regex.Pattern.quote(":"));
                    if (price.length == 2) { // Ensure the split result contains two parts
                        String date = intent.getStringExtra("date");
                        String time = intent.getStringExtra("time");

                        try {
                            float parsedPrice = Float.parseFloat(price[1]);
                            int parsedPincode = Integer.parseInt(edpincode.getText().toString());

                            Database db = new Database(getApplicationContext(), "healthCare", null, 1);
                            db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), parsedPincode, date, time, parsedPrice, "lab");
                            db.removeCart(username, "lab");
                            Toast.makeText(LabTestBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                        } catch (NumberFormatException e) {
                            Toast.makeText(LabTestBookActivity.this, "Invalid price or pincode format", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LabTestBookActivity.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LabTestBookActivity.this, "Price not provided", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}