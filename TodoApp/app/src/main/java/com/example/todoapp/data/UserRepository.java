package com.example.todoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import com.example.todoapp.model.ETodo;
import com.example.todoapp.model.EUser;
import com.example.todoapp.model.TodoRoomDatabase;

import java.util.List;


public class UserRepository {
    private UserDao mUserDao;

    public UserRepository(Application app) {
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(app);
        mUserDao = database.mUserDao();
    }

    public void insert(EUser eUser) {
        mUserDao.insert(eUser);
    }

    public void update(EUser eUser) {
        new updateUserAysncTask(mUserDao).execute(eUser);
    }

    public void deleteById(EUser eUser) {
        new deleteUserAysncTask(mUserDao).execute(eUser);
    }

    public EUser getUserById(int id) {
        return mUserDao.getUserById(id);
    }

    public List<EUser> getAllUsers() {
        return mUserDao.getAllUsers();
    }

    private static class updateUserAysncTask extends AsyncTask<EUser, Void, Void> {
        private UserDao mUserDao;
        private updateUserAysncTask(UserDao userDao){
            mUserDao=userDao;
        }

        @Override
        protected Void doInBackground(EUser... user) {
            mUserDao.update(user[0]);
            return null;
        }
    }

    private static class deleteUserAysncTask extends AsyncTask<EUser, Void, Void> {
        private UserDao mUserDao;
        private deleteUserAysncTask(UserDao userDao){
            mUserDao=userDao;
        }

        @Override
        protected Void doInBackground(EUser... user) {
            mUserDao.deleteById(user[0]);
            return null;
        }
    }
}
