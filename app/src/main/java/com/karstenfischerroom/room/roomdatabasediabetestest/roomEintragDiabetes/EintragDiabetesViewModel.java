package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

//ViewModel

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import java.util.List;

public class EintragDiabetesViewModel extends AndroidViewModel {
    private EintragDiabetesRepository repository;
    private LiveData<List<EintragDiabetes>> allNotes;
    public EintragDiabetesViewModel(@NonNull Application application) {
        super(application);
        repository=new EintragDiabetesRepository(application);
        allNotes=repository.getAllEintragDiabetes();
    }
    public void insert(EintragDiabetes eintragDiabetes){
        repository.insert(eintragDiabetes);
    }
    public void update(EintragDiabetes eintragDiabetes){
        repository.update(eintragDiabetes);
    }
    public void delete(EintragDiabetes eintragDiabetes){
        repository.delete(eintragDiabetes);
    }
    public void deleteAllNotes(){
        repository.deleteAllEintragDiabetes();
    }

    public LiveData<List<EintragDiabetes>> getAllEintragDiabetes() {
        return allNotes;
    }
}
