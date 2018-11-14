package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

//Repository
public class EintragDiabetesRepository {
    private EintragDiabetesDao eintragDiabetesDao;
    private LiveData<List<EintragDiabetes>> allEintragDiabetes;

    public EintragDiabetesRepository(Application application) {
        EintragDiabetesDatabase database = EintragDiabetesDatabase.getInstance(application);
        eintragDiabetesDao = database.eintragDiabetesDao();
        allEintragDiabetes = eintragDiabetesDao.getAllEintragDiabetes();
    }

    public void insert(EintragDiabetes eintragDiabetes) {
        new InsertEintragDiabetesAsyncTask(eintragDiabetesDao).execute(eintragDiabetes);

    }

    public void update(EintragDiabetes eintragDiabetes) {
        new UpdateEintragDiabetesAsyncTask(eintragDiabetesDao).execute(eintragDiabetes);
    }

    public void delete(EintragDiabetes eintragDiabetes) {
        new DeleteEintragDiabetesAsyncTask(eintragDiabetesDao).execute(eintragDiabetes);
    }

    public void deleteAllEintragDiabetes() {
        new DeleteAllEintragDiabetesAsyncTask(eintragDiabetesDao).execute();
    }

    public LiveData<List<EintragDiabetes>> getAllEintragDiabetes() {
        return allEintragDiabetes;
    }

    private static class InsertEintragDiabetesAsyncTask extends AsyncTask<EintragDiabetes, Void, Void> {
        private EintragDiabetesDao eintragDiabetesDao;
        private InsertEintragDiabetesAsyncTask(EintragDiabetesDao eintragDiabetesDao) {
            this.eintragDiabetesDao = eintragDiabetesDao;
        }
        @Override
        protected Void doInBackground(EintragDiabetes... eintragDiabetes) {
            eintragDiabetesDao.insert(eintragDiabetes[0]);
            return null;
        }
    }

    private static class UpdateEintragDiabetesAsyncTask extends AsyncTask<EintragDiabetes, Void, Void> {
        private EintragDiabetesDao eintragDiabetesDao;
        private UpdateEintragDiabetesAsyncTask(EintragDiabetesDao eintragDiabetesDao) {
            this.eintragDiabetesDao = eintragDiabetesDao;
        }
        @Override
        protected Void doInBackground(EintragDiabetes... eintragDiabetes) {
            eintragDiabetesDao.update(eintragDiabetes[0]);
            return null;
        }
    }

    private static class DeleteEintragDiabetesAsyncTask extends AsyncTask<EintragDiabetes, Void, Void> {
        private EintragDiabetesDao eintragDiabetesDao;
        private DeleteEintragDiabetesAsyncTask(EintragDiabetesDao eintragDiabetesDao) {
            this.eintragDiabetesDao = eintragDiabetesDao;
        }
        @Override
        protected Void doInBackground(EintragDiabetes... eintragDiabetes) {
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
