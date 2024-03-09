package com.example.autoair;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtEmail, edtPassword;
    Button btnLogin;

    private FirebaseAuth mAuth;

    Intent intent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        btnLogin = findViewById(R.id.btnLogin);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String enteredUsername = edtUsername.getText().toString();
//                String enteredPassword = edtPassword.getText().toString();
//
//                // Attach a ValueEventListener to check if the entered credentials exist in the database
//                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        boolean credentialsMatched = false;
//
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            String key = snapshot.getKey(); // Get the key
//                            String username = snapshot.child("username").getValue(String.class);
//                            String password = snapshot.child("password").getValue(String.class);
//
//                            Toast.makeText(MainActivity.this, "Key: " + key, Toast.LENGTH_SHORT).show();
//
//                            // Check if the entered credentials match any user in the database
//                            if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
//                                credentialsMatched = true;
//                                break;
//                            }
//                        }
//
//                        if (credentialsMatched) {
//                            // Login successful, handle the login logic (e.g., start a new activity)
//                            // For example, you can start ApplianceControls activity:
//                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                            redirectActivity(HomePage.class);
//                        } else {
//                            // Display a message indicating invalid credentials (e.g., show a Toast)
//                            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        // Handle errors, if any
//                    }
//                });
//            }
//        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                // Check if fields are empty
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please complete all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(MainActivity.this, "Login successfully.", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                redirectActivity(HomePage.class);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Failed to retrieve data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
            finish();
        }

    }

    public class User {
        public String username;
        public String password;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

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