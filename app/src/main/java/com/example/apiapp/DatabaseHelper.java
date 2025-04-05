package com.example.apiapp;

import android.content.Context;

import androidx.room.Room;

public class DatabaseHelper {

    private static volatile AppDatabase appDatabase;

    public static AppDatabase getDatabase(Context context) {
        if (appDatabase == null) {
            synchronized (DatabaseHelper.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app-db"
                            )
                            .fallbackToDestructiveMigration() // Добавлено для избежания крашей при изменении схемы
                            .build();
                }
            }
        }
        return appDatabase;
    }
}

