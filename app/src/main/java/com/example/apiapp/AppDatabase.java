package com.example.apiapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract PostDao postDao(); // Доступ к DAO

    // Метод для получения экземпляра базы данных (Singleton)
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "post_database"
                            )
                            .fallbackToDestructiveMigration() // Добавлено, чтобы избежать краша при изменении схемы
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


