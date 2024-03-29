package com.example.assignment2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRestaurants;
    RestaurantAdapter restaurantAdapter;
    ArrayList<Restaurant> restaurantList;
    Button btnAddNewRestaurant;
    Button btnFilter;

    private static final int ADD_RESTAURANT_REQUEST_CODE = 1;
    private static final int FILTER_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        rvRestaurants.setHasFixedSize(true);
        restaurantAdapter = new RestaurantAdapter(restaurantList);

        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurants.setAdapter(restaurantAdapter);

        btnAddNewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
                startActivityForResult(intent, ADD_RESTAURANT_REQUEST_CODE);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                startActivityForResult(intent, FILTER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_RESTAURANT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String location = data.getStringExtra("location");
            String phone = data.getStringExtra("phone");
            String description = data.getStringExtra("description");
            int rating = data.getIntExtra("rating", 0);

            restaurantList.add(new Restaurant(name, location, phone, description, rating));
            Toast.makeText(MainActivity.this, "New restaurant added", Toast.LENGTH_SHORT).show();
            restaurantAdapter.notifyDataSetChanged();
        } else if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int minRating = data.getIntExtra("minRating", 0);
            filterRestaurants(minRating);
        }
    }

    private void init() {
        restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Lahori Chaska", "Gulberg, Lahore", "+92 345 678901", "Traditional Lahori cuisine", 4));
        restaurantList.add(new Restaurant("Karachi Darbar", "Saddar, Karachi", "+92 321 9876543", "Famous for Karachi biryani", 5));
        restaurantList.add(new Restaurant("Peshawari Chapli Kebab", "University Road, Peshawar", "+92 333 4567890", "Specializes in Chapli Kebabs", 4));
        restaurantList.add(new Restaurant("Islamabad Grill House", "F-10 Markaz, Islamabad", "+92 300 1234567", "Best barbecue in town", 4));
        restaurantList.add(new Restaurant("Quetta Kabab House", "G-9, Islamabad", "+92 333 9876543", "Known for Quetta-style kebabs", 3));
        restaurantList.add(new Restaurant("Rawalpindi Food Street", "Murree Road, Rawalpindi", "+92 321 2345678", "Offers a variety of street foods", 3));
        restaurantList.add(new Restaurant("Hyderabadi Biryani Hut", "Gulshan-e-Iqbal, Karachi", "+92 333 8765432", "Authentic Hyderabadi biryani", 5));
        restaurantList.add(new Restaurant("Faisalabad BBQ Delight", "Gulberg, Faisalabad", "+92 301 7654321", "Specializes in Faisalabadi barbecue dishes", 4));
        restaurantList.add(new Restaurant("Multani Sajji House", "Bahawalpur Road, Multan", "+92 333 3456789", "Famous for Multani Sajji", 4));
        restaurantList.add(new Restaurant("Larkana Sindhi Cuisine", "Qasimabad, Larkana", "+92 302 4567890", "Offers traditional Sindhi dishes", 3));

        rvRestaurants = findViewById(R.id.rvRestaurants);
        btnAddNewRestaurant = findViewById(R.id.btnAddNewRestaurant);
        btnFilter = findViewById(R.id.btnFilter);
    }

    private void filterRestaurants(int minRating) {
        List<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRating() >= minRating) {
                filteredList.add(restaurant);
            }
        }
        restaurantAdapter.setRestaurantList(filteredList);
        restaurantAdapter.notifyDataSetChanged();
    }
}
