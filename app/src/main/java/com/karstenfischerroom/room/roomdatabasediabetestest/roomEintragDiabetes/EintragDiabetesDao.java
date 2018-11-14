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
    void insert(EintragDiabetes eintragDiabetes);

    @Update
    void update(EintragDiabetes eintragDiabetes);

    @Delete
    void delete(EintragDiabetes eintragDiabetes);

    @Query("DELETE FROM eintrag_diabetes_table")
    void deleteAllEintragDiabetes();

    @Query("SELECT * FROM eintrag_diabetes_table ORDER BY currentTimeMillis DESC")
    LiveData<List<EintragDiabetes>> getAllEintragDiabetes();
}
