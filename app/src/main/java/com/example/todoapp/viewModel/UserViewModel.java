package com.example.todoapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.todoapp.data.TodoRepository;
import com.example.todoapp.data.UserRepository;
import com.example.todoapp.model.ETodo;
import com.example.todoapp.model.EUser;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    UserRepository repository;
    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void insert(EUser user) {
        repository.insert(user);
    }

    public void update(EUser user) {repository.update(user);}

    public void deleteById(EUser user) {repository.deleteById(user);}

    public EUser getUserById(int id) {
        return repository.getUserById(id);
    }

    public List<EUser> getAllUsers() {
       return repository.getAllUsers();
    }

}
