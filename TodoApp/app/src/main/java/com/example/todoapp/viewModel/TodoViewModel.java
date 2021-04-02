package com.example.todoapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapp.data.TodoRepository;
import com.example.todoapp.model.ETodo;
import com.example.todoapp.model.EUser;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository repository;
    private LiveData<List<ETodo>> allTodos;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
        allTodos = repository.getAllTodoList();
    }

    public LiveData<List<ETodo>> getAllTodos() {return allTodos;}

    public void insert(ETodo todo) { repository.insert(todo);}
    public void update(ETodo todo) {repository.update(todo);}

    public void updateIsCompleted(int id, boolean is_completed) { repository.updateIsCompleted(id, is_completed);}

    public void deleteById(ETodo eTodo){
        repository.deleteById(eTodo);
    }

    public void deleteAll(int id) {repository.deleteAll(id);}

    public List<ETodo> getAll() {return repository.getAll();}

    public ETodo getTodoById(int id){
        return repository.getTodoById(id);
    }

    public void deleteAllCompleted(int id, boolean is_completed) { repository.deleteAllCompleted(id, is_completed); }

}
