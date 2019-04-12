package com.ferinabay.noteapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.ferinabay.noteapp.dao.NoteDao;
import com.ferinabay.noteapp.dao.UserDao;
import com.ferinabay.noteapp.models.Note;
import com.ferinabay.noteapp.models.User;


@Database(entities = {User.class, Note.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract NoteDao noteDao();
    private static final String DB_NAME = "notes";

    private static volatile  AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(final Context context) {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }


}
