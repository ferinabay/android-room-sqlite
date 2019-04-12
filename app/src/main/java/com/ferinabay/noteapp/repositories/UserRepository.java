package com.ferinabay.noteapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.ferinabay.noteapp.dao.UserDao;
import com.ferinabay.noteapp.db.AppDatabase;
import com.ferinabay.noteapp.models.User;


public class UserRepository {
    private UserDao dao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        dao = db.userDao();
    }

    public LiveData<User> getUserByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    public void insert(User user) {
        new InsertAsyncTask(dao).execute(user);
    }

    static class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao asyncTaskDao;

        InsertAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.insert(users);
            return null;
        }

    }
}
