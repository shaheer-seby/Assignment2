package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FilterActivity extends AppCompatActivity {

    EditText edtMinRating;
    Button btnApplyFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        edtMinRating = findViewById(R.id.edtMinRating);
        btnApplyFilter = findViewById(R.id.btnApplyFilter);

        btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the minimum rating entered by the user
                String minRating = edtMinRating.getText().toString().trim();

                // If the input is not empty, pass it back to the MainActivity
                if (!minRating.isEmpty()) {
                    int minRatingValue = Integer.parseInt(minRating);
                    Intent intent = new Intent();
                    intent.putExtra("minRating", minRatingValue);
                    setResult(RESULT_OK, intent);
                } else {
                    setResult(RESULT_CANCELED);
                }

                // Finish the activity
                finish();
            }
        });
    }
}
