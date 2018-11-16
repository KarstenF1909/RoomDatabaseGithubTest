package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

//ViewModel

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import java.util.List;

public class EintragDiabetesViewModel extends AndroidViewModel {
    private EintragDiabetesRepository repository;
    private LiveData<List<Note>> allNotes;
    public EintragDiabetesViewModel(@NonNull Application application) {
        super(application);
        repository=new EintragDiabetesRepository(application);
        allNotes=repository.getAllEintragDiabetes();
    }
    public void insert(Note note){
        repository.insert(note);
    }
    public void update(Note note){
        repository.update(note);
    }
    public void delete(Note note){



        repository.delete(note);




    }
    public void deleteAllNotes(){
        repository.deleteAllEintragDiabetes();
    }

    public LiveData<List<Note>> getAllEintragDiabetes() {
        return allNotes;
    }
}
