package com.example.autoair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class ApplianceControls extends AppCompatActivity {

    CardView card1, card2;
    LinearLayout layoutC1, layoutC1S, layoutC2, layoutC2S;

    Switch switchC1, switchC1S1, switchC2;

    ImageButton imgBtnMode, imgBtnProgramDetails, imgBtnFrequency, imgBtnTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_controls);

        card1 = findViewById(R.id.card1); // Controls Card
        layoutC1 = findViewById(R.id.layoutC1); // Controls layout
        switchC1 = findViewById(R.id.switchC1); // Controls switch
        layoutC1S = findViewById(R.id.layoutC1S); // Off/On layout
        switchC1S1 = findViewById(R.id.switchC1S1); // Off/On switch

        switchC1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchC2.setChecked(false);

                    layoutC1S.setAlpha(1F);
                    layoutC1S.setClickable(true);
                    switchC1S1.setClickable(true);
                } else {
                    layoutC1S.setAlpha(0.6F);
                    layoutC1S.setClickable(false);
                    switchC1S1.setChecked(false);
                    switchC1S1.setClickable(false);
                }
            }
        });

        card2 = findViewById(R.id.card2);
        layoutC2 = findViewById(R.id.layoutC2);
        switchC2 = findViewById(R.id.switchC2);
        layoutC2S = findViewById(R.id.layoutC2S);

        imgBtnMode = findViewById(R.id.imgBtnMode);
        imgBtnProgramDetails = findViewById(R.id.imgBtnProgramDetails);
        imgBtnFrequency = findViewById(R.id.imgBtnFrequency);
        imgBtnTime = findViewById(R.id.imgBtnTime);

        switchC2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchC1.setChecked(false);

                    layoutC2S.setAlpha(1F);
                    layoutC2S.setClickable(true);
                    imgBtnMode.setClickable(true);
                    imgBtnProgramDetails.setClickable(true);
                    imgBtnFrequency.setClickable(true);
                    imgBtnTime.setClickable(true);
                } else {
                    layoutC2S.setAlpha(0.6F);
                    layoutC2S.setClickable(false);
                    imgBtnMode.setClickable(false);
                    imgBtnProgramDetails.setClickable(false);
                    imgBtnFrequency.setClickable(false);
                    imgBtnTime.setClickable(false);
                }
            }
        });














        imgBtnProgramDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowProgramDetails();
            }
        });
















        // Toolbar
        View toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Appliance Controls");

        ImageView icon1 = toolbar.findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.ic_chevron_left);

        ImageView icon2 = toolbar.findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.ic_notification);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ShowProgramDetails(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ac_program_details);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}