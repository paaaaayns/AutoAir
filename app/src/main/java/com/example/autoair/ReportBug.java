package com.example.autoair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class ReportBug extends AppCompatActivity {


    Intent intent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bug);

        // Toolbar
        View toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Report a Bug");

        ImageButton icon1 = toolbar.findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.ic_chevron_left);


        ImageButton icon2 = toolbar.findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.ic_notification);


        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}