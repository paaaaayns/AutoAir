package com.example.autoair;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddAppliancePage extends AppCompatActivity {

    String[] itemRoom = {"Room 1", "Room 2", "Room 3", "Room 4"};


    AutoCompleteTextView autoCompleteAppliance, autoCompleteRoom;
    ArrayAdapter<String> adapterAppliance, adapterRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appliance_page);


        autoCompleteRoom = findViewById(R.id.autoCompleteRoom);
        adapterRoom = new ArrayAdapter<String>(this, R.layout.dropdown_items, itemRoom);
        autoCompleteRoom.setAdapter(adapterRoom);

        autoCompleteRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                Toast.makeText(AddAppliancePage.this, "Seleceted: " + item, Toast.LENGTH_SHORT).show();
            }
        });














        // Toolbar
        View toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Add Appliances");

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
}