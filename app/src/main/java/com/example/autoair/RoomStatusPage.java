package com.example.autoair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomStatusPage extends AppCompatActivity {

    CardView card1, card2, card3, card4;
    TextView tvRoomTemperature, tvRoomHumidity, tvRoomPower;

    Intent intent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status_page);



        tvRoomTemperature = findViewById(R.id.tvRoomTemperature);
        tvRoomHumidity = findViewById(R.id.tvRoomHumidity);
        tvRoomPower = findViewById(R.id.tvRoomPower);

        intent = new Intent(getApplicationContext(), RoomStatusPage.class);

        // Retrieve the passed values
        intent = getIntent();
        String floorKey = intent.getStringExtra("key");
        String floorName = intent.getStringExtra("name");
        //Checking
        //Toast.makeText(this, "Item Name: " + floorName, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Item Key: " + floorKey, Toast.LENGTH_SHORT).show();

        DatabaseReference floorRef = database.getInstance().getReference("company").child("CSC").child("floors").child(floorKey);
        DatabaseReference applianceRef = floorRef.child("appliances");
        DatabaseReference sensorRef = floorRef.child("sensors");

        sensorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer temperature = snapshot.child("temperature").getValue(Integer.class);
                Integer humidity = snapshot.child("humidity").getValue(Integer.class);
                Integer power = snapshot.child("power").getValue(Integer.class);

                // Convert the values to string only if they are not null
                String temperatureString = (temperature != null) ? temperature.toString() : "N/A";
                String humidityString = (humidity != null) ? humidity.toString() : "N/A";
                String powerString = (power != null) ? power.toString() : "N/A";

                // Set the values to your TextViews
                tvRoomTemperature.setText(temperatureString);
                tvRoomHumidity.setText(humidityString);
                tvRoomPower.setText(powerString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        // In your activity or fragment code
        LinearLayout mainLayout = findViewById(R.id.layoutApplianceList);

        applianceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get key of the snapshot
                    String applianceKey = snapshot.getKey().toString();

                    // Get the values of the snapshot
                    String appliance_name = snapshot.child("appliance_name").getValue(String.class);
                    String appliance_on_sched = snapshot.child("appliance_on_sched").getValue(String.class);
                    String appliance_off_sched = snapshot.child("appliance_off_sched").getValue(String.class);
                    Boolean appliance_power_status = snapshot.child("appliance_power_status").getValue(Boolean.class);

                    // Inflate the item layout
                    View itemLayout = getLayoutInflater().inflate(R.layout.layout_appliance_status, null);

                    // Customize the item layout based on the data from the snapshot
                    TextView tvTitle = itemLayout.findViewById(R.id.tvTitle);
                    tvTitle.setText(appliance_name);

                    TextView tvSchedule = itemLayout.findViewById(R.id.tvSchedule);
                    tvSchedule.setText(appliance_on_sched + " - " + appliance_off_sched);

                    ImageView imgStatus = itemLayout.findViewById(R.id.imgStatus);
                    TextView tvStatus = itemLayout.findViewById(R.id.tvStatus);
                    Switch toggleSwitch = itemLayout.findViewById(R.id.toggleSwitch);
                    toggleSwitch.setChecked(appliance_power_status);
                    toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            int tint;
                            String status;

                            if (isChecked) { // Switch is ON
                                // Set element changes
                                tint = ContextCompat.getColor(getApplicationContext(), R.color.switchOn);
                                status = "on";
                            } else { // Switch is OFF
                                // Set element changes
                                tint = ContextCompat.getColor(getApplicationContext(), R.color.switchOff);
                                status = "off";
                            }

                            // Apply changes
                            imgStatus.setColorFilter(tint);
                            tvStatus.setText(status);

                            // Update appliance_power_status in the database
                            DatabaseReference applianceChildRef = applianceRef.child(applianceKey).child("appliance_power_status");
                            applianceChildRef.setValue(isChecked);
                        }
                    });


                    // Add the inflated item layout to the main layout
                    mainLayout.addView(itemLayout);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });

        // Assuming you have a DatabaseReference reference pointing to your data
        // Iterate through the data from Firebase and inflate the item layout for each item




















        // Toolbar
        View toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText(floorName);

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

    // Redirect
    public void redirectActivity(Class secondActivity) {
        Intent intent = new Intent(getApplicationContext(), secondActivity);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}