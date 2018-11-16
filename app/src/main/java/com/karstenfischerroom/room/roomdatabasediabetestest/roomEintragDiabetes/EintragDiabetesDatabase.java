package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

@Database(entities = {Note.class}, version = 1)
public abstract class EintragDiabetesDatabase extends RoomDatabase {

    private static EintragDiabetesDatabase instance;

    public abstract EintragDiabetesDao eintragDiabetesDao();

    public static synchronized EintragDiabetesDatabase getInstance(Context context){

        if(instance==null){
            instance=Room.databaseBuilder(context.getApplicationContext(),
                    EintragDiabetesDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private EintragDiabetesDao eintragDiabetesDao;

        private PopulateDbAsyncTask(EintragDiabetesDatabase db){
            eintragDiabetesDao =db.eintragDiabetesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eintragDiabetesDao.insert(new Note("Title 1",
                    "Description 1",
                    1,
                    111,
                    1,
                    1,
                    1,
                    1,
                    "06.06.666",
                    "6:06",
                    111111,
                    555));


            return null;
        }
    }

}
