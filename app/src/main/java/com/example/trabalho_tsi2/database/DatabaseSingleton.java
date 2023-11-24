package com.example.trabalho_tsi2.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseSingleton {
    public DatabaseReference profiles;
    private static DatabaseSingleton instance;

    private DatabaseSingleton() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        profiles = database.child("profiles");
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            DatabaseSingleton.instance = new DatabaseSingleton();
        }
        
        return instance;
    }
}
