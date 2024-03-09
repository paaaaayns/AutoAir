package com.example.autoair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;

    LinearLayout layoutDashboard, layoutAppearance, layoutReportBug, layoutSettings, layoutLogout;

    CardView cardAddAppliance, cardAddRoom;

    Intent intent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        intent = new Intent(getApplicationContext(), RoomStatusPage.class);

        DatabaseReference floorRef = database.getInstance().getReference("company").child("CSC").child("floors");


        // In your activity or fragment code
        LinearLayout mainLayout = findViewById(R.id.layoutFloorList);

        // Assuming you have a DatabaseReference reference pointing to your data
        // Iterate through the data from Firebase and inflate the item layout for each item
        floorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the key of the snapshot
                    String key = snapshot.getKey().toString();

                    // Get the value of the snapshot
                    String name = snapshot.child("name").getValue(String.class);

                    // Inflate the item layout
                    View itemLayout = getLayoutInflater().inflate(R.layout.layout_floor_card, null);

                    // Customize the item layout based on the data from the snapshot
                    TextView tvTitle = itemLayout.findViewById(R.id.tvTitle);
                    tvTitle.setText(name);

                    // Add the inflated item layout to the main layout
                    mainLayout.addView(itemLayout);

                    itemLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent.putExtra("key", key);
                            intent.putExtra("name", name);
                            redirectActivity(RoomStatusPage.class);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });


        // Toolbar
        View toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Auto Air");

        ImageButton icon1 = toolbar.findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.ic_menu);

        ImageButton icon2 = toolbar.findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.ic_notification);


        drawerLayout = findViewById(R.id.drawerLayout);
        layoutDashboard = findViewById(R.id.layoutDashboard);
        layoutAppearance = findViewById(R.id.layoutAppearance);
        layoutReportBug = findViewById(R.id.layoutReportBug);
        layoutSettings = findViewById(R.id.layoutSettings);
        layoutLogout = findViewById(R.id.layoutLogout);

        // Open Navigation Drawer
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });


        // Redirect Page - Home Page/Map Page
        layoutDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });


        // Redirect Page - Report Bug Page
        layoutReportBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer(drawerLayout);
                redirectActivity(ReportBug.class);
            }
        });



        // Redirect Page - Logout
        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomePage.this, "Account signed out.", Toast.LENGTH_SHORT).show();
                redirectActivity(MainActivity.class);
                finish();
            }
        });














        View card_appliances = findViewById(R.id.card_appliances);
        card_appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ApplianceControls.class);
            }
        });

//        View card_rooms = findViewById(R.id.card_rooms);
//        card_rooms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                redirectActivity(RoomStatusPage.class);
//            }
//        });


















    }




    // OPEN NAVIGATION DRAWER
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    // CLOSE NAVIGATION DRAWER
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // REDIRECT ON NAVIGATION DRAWER CLICK
    public void redirectActivity(Class secondActivity) {
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    private boolean backPressedOnce = false;
    @Override
    public void onBackPressed() {

        if (backPressedOnce) {
            super.onBackPressed();
            finish();
        } else {
            this.backPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            // Reset the flag after a certain time period
            new Handler().postDelayed(() -> backPressedOnce = false, 2000);
        }
    }
}