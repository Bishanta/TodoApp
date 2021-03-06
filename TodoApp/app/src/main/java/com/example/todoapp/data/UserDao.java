package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.model.ETodo;
import com.example.todoapp.model.EUser;

import java.util.List;
@Dao
public interface UserDao {

    @Insert
    void insert(EUser todo);



    @Query("SELECT * FROM user_table WHERE user_id=:id")
    EUser getUserById(int id);

    @Delete
    void deleteById(EUser eUser);

    //fetch all uesrs
    @Query("SELECT * FROM user_table")
      List<EUser> getAllUsers();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(EUser... user);


}
