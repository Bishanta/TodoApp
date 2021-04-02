package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.model.ETodo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    void insert(ETodo todo);

    @Query("DELETE FROM todo_table WHERE user_id = :id")
    void deleteAll(int id);

    @Query("DELETE FROM todo_table WHERE user_id=:id AND is_completed = :is_completed")
    void deleteAllCompleted(int id, boolean is_completed);
    @Delete
    void deleteById(ETodo todo);

    @Query("SELECT * FROM todo_table WHERE id=:id")
    ETodo getTodoById(int id);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ETodo... todo);

    @Query("UPDATE todo_table SET is_completed = :is_completed WHERE id = :id")
    void updateIsComplete(int id, boolean is_completed);

    /**
     * Observable queries are read operations that emit new values whenever there are changes
     * to any of the tables that are referenced by the query.
     * @return list of todos in desc
     */
    @Query("SELECT * FROM todo_table ORDER BY todo_date, priority desc")
    LiveData<List<ETodo>> getAllTodos();

    @Query("SELECT * FROM todo_table ORDER BY todo_date, priority desc")
    List<ETodo> getAll();


}
