package com.example.autoair;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {

    Connection con;

    public Connection connectionclass(){
        String ip = "192.168.0.84";
        String port = "49928";
        String username = "simon";
        String password = "P@ssw0rd";
        String databasename = "MicroDataSysDB";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";user=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return connection;
    }
}
