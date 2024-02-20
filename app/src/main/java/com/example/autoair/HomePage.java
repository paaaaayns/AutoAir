package com.example.autoair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;

    LinearLayout layoutDashboard, layoutAppearance, layoutReportBug, layoutSettings, layoutLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        drawerLayout = findViewById(R.id.drawerLayout);
        layoutDashboard = findViewById(R.id.layoutDashboard);
        layoutAppearance = findViewById(R.id.layoutAppearance);
        layoutReportBug = findViewById(R.id.layoutReportBug);
        layoutSettings = findViewById(R.id.layoutSettings);
        layoutLogout = findViewById(R.id.layoutLogout);




        View card_appliances = findViewById(R.id.card_appliances);
        card_appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ApplianceControls.class);
            }
        });

        View card_rooms = findViewById(R.id.card_rooms);
        card_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(RoomStatusPage.class);
            }
        });







        // Toolbar
        View toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Auto Air");

        ImageView icon1 = toolbar.findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.ic_menu);

        ImageView icon2 = toolbar.findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.ic_notification);


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
                finish();
            }
        });





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
        Intent intent = new Intent(getApplicationContext(), secondActivity);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomePage.this, "Account signed out.", Toast.LENGTH_SHORT).show();
        redirectActivity(MainActivity.class);
        finish();
    }
}