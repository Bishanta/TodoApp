package com.example.todoapp.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoapp.data.TodoDao;
import com.example.todoapp.data.UserDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defines the database configuration and serves as the app's main access point to the persisted data.
 */
@Database(entities = {ETodo.class, EUser.class}, version = 1, exportSchema = false)
public abstract class TodoRoomDatabase extends RoomDatabase {
    /**
     * The database class must define an abstract method that has zero arguments
     * and returns an instance of the DAO class, for each DAO class.
     * @return TodoDao
     */
    public abstract TodoDao mTodoDao();

    public abstract UserDao mUserDao();

    public static TodoRoomDatabase INSTANCE;

    public static TodoRoomDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            synchronized (TodoRoomDatabase.class){
                if(INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoRoomDatabase.class,
                            "todo.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }

    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodoDao todoDao;
        private UserDao userDao;

        private PopulateDbAsyncTask(TodoRoomDatabase db) {
            todoDao = db.mTodoDao();
            userDao = db.mUserDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * Inserting data in todo_table
             */
            Date todoDate = new Date();
            try{
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                todoDate = format.parse("2020/11/22");
            }catch (ParseException ex) {
                ex.printStackTrace();
            }

            todoDao.insert(new ETodo("Get some milk!", "Milk costs around 1$ per litre. So, buy three litres!",
                    todoDate, 1, false, 0));
            todoDao.insert(new ETodo("Go jogging with you friends.", "Jogging is a good cardio and is also beneficial for your mental health!",
                    todoDate, 2, false, 0));
            todoDao.insert(new ETodo("Get some milk!", "Milk costs around 1$ per litre. So, buy three litres!",
                    todoDate, 3, false, 0));

            /**
             * Inserting data in user_table
             */
            userDao.insert(new EUser(0,"Bishant", "123"));
            return null;
        }
    }

}
