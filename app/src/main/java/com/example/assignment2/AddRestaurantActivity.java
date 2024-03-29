package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRestaurantActivity extends AppCompatActivity {

    EditText edtName, edtLocation, edtPhone, edtDescription, edtRating;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        edtName = findViewById(R.id.edtName);
        edtLocation = findViewById(R.id.edtLocation);
        edtPhone = findViewById(R.id.edtPhone);
        edtDescription = findViewById(R.id.edtDescription);
        edtRating = findViewById(R.id.edtRating);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String location = edtLocation.getText().toString();
                String phone = edtPhone.getText().toString();
                String description = edtDescription.getText().toString();
                int rating = Integer.parseInt(edtRating.getText().toString());

                // Check if the rating is within the valid range (1-5)
                if (rating < 1 || rating > 5) {
                    // Show an error message if the rating is invalid
                    edtRating.setError("Rating must be between 1 and 5");
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("location", location);
                resultIntent.putExtra("phone", phone);
                resultIntent.putExtra("description", description);
                resultIntent.putExtra("rating", rating); // Pass the rating back to MainActivity
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
