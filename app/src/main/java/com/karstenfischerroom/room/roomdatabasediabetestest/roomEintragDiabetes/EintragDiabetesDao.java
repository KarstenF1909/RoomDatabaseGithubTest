package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Dao erstellen (Dao muß Interface oder Abstract Class sein)  Data Access Object
@Dao
public interface EintragDiabetesDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllEintragDiabetes();

    @Query("SELECT * FROM note_table ORDER BY currentTimeMillis DESC")
    LiveData<List<Note>> getAllEintragDiabetes();
}
