package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

//Repository
public class EintragDiabetesRepository {
    private EintragDiabetesDao eintragDiabetesDao;
    private LiveData<List<Note>> allEintragDiabetes;

    public EintragDiabetesRepository(Application application) {
        EintragDiabetesDatabase database = EintragDiabetesDatabase.getInstance(application);
        eintragDiabetesDao = database.eintragDiabetesDao();
        allEintragDiabetes = eintragDiabetesDao.getAllEintragDiabetes();
    }

    public void insert(Note note) {
        new InsertEintragDiabetesAsyncTask(eintragDiabetesDao).execute(note);

    }

    public void update(Note note) {
        new UpdateEintragDiabetesAsyncTask(eintragDiabetesDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteEintragDiabetesAsyncTask(eintragDiabetesDao).execute(note);
    }

    public void deleteAllEintragDiabetes() {
        new DeleteAllEintragDiabetesAsyncTask(eintragDiabetesDao).execute();
    }

    public LiveData<List<Note>> getAllEintragDiabetes() {
        return allEintragDiabetes;
    }

    private static class InsertEintragDiabetesAsyncTask extends AsyncTask<Note, Void, Void> {
        private EintragDiabetesDao eintragDiabetesDao;
        private InsertEintragDiabetesAsyncTask(EintragDiabetesDao eintragDiabetesDao) {
            this.eintragDiabetesDao = eintragDiabetesDao;
        }
        @Override
        protected Void doInBackground(Note... eintragDiabetes) {
            eintragDiabetesDao.insert(eintragDiabetes[0]);
            return null;
        }
    }

    private static class UpdateEintragDiabetesAsyncTask extends AsyncTask<Note, Void, Void> {
        private EintragDiabetesDao eintragDiabetesDao;
        private UpdateEintragDiabetesAsyncTask(EintragDiabetesDao eintragDiabetesDao) {
            this.eintragDiabetesDao = eintragDiabetesDao;
        }
        @Override
        protected Void doInBackground(Note... eintragDiabetes) {
            eintragDiabetesDao.update(eintragDiabetes[0]);
            return null;
        }
    }

    private static class DeleteEintragDiabetesAsyncTask extends AsyncTask<Note, Void, Void> {
        private EintragDiabetesDao eintragDiabetesDao;
        private DeleteEintragDiabetesAsyncTask(EintragDiabetesDao eintragDiabetesDao) {
            this.eintragDiabetesDao = eintragDiabetesDao;
        }
        @Override
        protected Void doInBackground(Note... eintragDiabetes) {
            eintragDiabetesDao.delete(eintragDiabetes[0]);
            return null;
        }
    }

    private static class DeleteAllEintragDiabetesAsyncTask extends AsyncTask<Void, Void, Void> {
        private EintragDiabetesDao eintragDiabetesDao;
        private DeleteAllEintragDiabetesAsyncTask(EintragDiabetesDao eintragDiabetesDao) {
            this.eintragDiabetesDao = eintragDiabetesDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            eintragDiabetesDao.deleteAllEintragDiabetes();
            return null;
        }
    }

}
