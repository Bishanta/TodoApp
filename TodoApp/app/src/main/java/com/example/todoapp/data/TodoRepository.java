package com.example.todoapp.data;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import com.example.todoapp.model.ETodo;
import com.example.todoapp.model.TodoRoomDatabase;

public class TodoRepository {
    private TodoDao mTodoDAO;
    private LiveData<List<ETodo>> allTodoList;

    public TodoRepository(Application application){
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        mTodoDAO = database.mTodoDao();
        allTodoList = mTodoDAO.getAllTodos();
    }

    public TodoDao getmTodoDAO() {
        return mTodoDAO;
    }

    public void setmTodoDAO(TodoDao mTodoDAO) {
        this.mTodoDAO = mTodoDAO;
    }

    public LiveData<List<ETodo>> getAllTodoList() {
        return allTodoList;
    }

    public void setAllTodoList(LiveData<List<ETodo>> allTodoList) {
        this.allTodoList = allTodoList;
    }

    public void insert(ETodo eTodo){
        new insertTodoAysncTask(mTodoDAO).execute(eTodo);
    }

    public void update(ETodo eTodo) {
        new updateTodoAysncTask(mTodoDAO).execute(eTodo);
    }

    public void updateIsCompleted(int id, boolean is_completed) {mTodoDAO.updateIsComplete(id, is_completed);}

    public void deleteById(ETodo eTodo){
        new deleteByIdTodoAysnc(mTodoDAO).execute(eTodo);
    }

    public void deleteAll(int id) { mTodoDAO.deleteAll(id);}

    public List<ETodo> getAll() { return  mTodoDAO.getAll();}

    public void deleteAllCompleted(int id, boolean is_completed) {
        mTodoDAO.deleteAllCompleted(id, is_completed);
    }

    public ETodo getTodoById(int id){
        return mTodoDAO.getTodoById(id);
    }

    private static class insertTodoAysncTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDao mTodoDao;
        private insertTodoAysncTask(TodoDao todoDAO){
            mTodoDao=todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... eTodos) {
            mTodoDao.insert(eTodos[0]);
            return null;
        }
    }

    private static class updateTodoAysncTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDao mTodoDao;
        private updateTodoAysncTask(TodoDao todoDAO){
            mTodoDao=todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... eTodos) {
            mTodoDao.update(eTodos[0]);
            return null;
        }
    }
    private static class deleteByIdTodoAysnc extends AsyncTask<ETodo, Void, Void>{
        private TodoDao mTodoDao;
        private deleteByIdTodoAysnc(TodoDao todoDAO){
            mTodoDao=todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... eTodos) {
            mTodoDao.deleteById(eTodos[0]);
            return null;
        }
    }


}
