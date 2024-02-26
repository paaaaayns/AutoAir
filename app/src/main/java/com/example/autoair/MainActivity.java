package com.example.autoair;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtUsername, edtPassword;
    Button btnLogin;

    Connection connect;
    String ConnectionResult="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);







        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();

                    if (connect != null) {
                        String query = "SELECT * FROM tblUsers WHERE 'username' = '"+ username +"' AND 'password' = '"+ password +"'";
                        Statement st = connect.createStatement();
                        ResultSet rs = st.executeQuery(query);

                        if (rs != null){
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }

                    }


                } catch (Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });






    }





//    @SuppressLint("NewApi")
//    public Connection connectionclass(){
//        Connection conn=null;
//        String ip = "172.1.1.0";
//        String port = "49928";
//        String username = "simon";
//        String password = "P@ssw0rd";
//        String databasename = "MicroDataSysDB";
//
//        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(tp);
//
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";user=" + username + ";password=" + password + ";";
//            conn = DriverManager.getConnection(connectionUrl);
//        } catch (Exception exception){
//            Log.e("Error", exception.getMessage());
//        }
//
//        return conn;
//
//    }


    public void redirectActivity(Class secondActivity) {
        Intent intent = new Intent(getApplicationContext(), secondActivity);
        startActivity(intent);
    }

}