package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.karstenfischerroom.room.roomdatabasediabetestest.R;
import com.karstenfischerroom.room.roomdatabasediabetestest.TTS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEditEintragDiabetesActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String EXTRA_ID = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_DECRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_PRIORITY";
    public static final String EXTRA_BLUTZUCKER =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BLUTZUCKER";
    public static final String EXTRA_BE =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BE";
    public static final String EXTRA_BOLUS =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BOLUS";
    public static final String EXTRA_KORREKTUR =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_KORREKTUR";
    public static final String EXTRA_BASAL =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BASAL";

    public static final String EXTRA_DATUM =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_DATUM";
    public static final String EXTRA_UHRZEIT =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_UHRZEIT";

    public static final String EXTRA_CURRENT_TIME_MILLIS =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_TIMESTAMP_SYSTEM";

    public static final String EXTRA_EINTRAG_DATUM_MILLIS = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_EINTRAG_DATUM_MILLIS";


    private EditText etTitle;
    private EditText etDescription;
    private NumberPicker npPriority;
    private EditText etBlutzucker;
    private EditText etBe;
    private EditText etBolus;
    private EditText etKorrektur;
    private EditText etBasal;

    private TextView tvDatum;
    private TextView tvUhrzeit;


    private String datum;
    private String uhrzeit;
    private long currentTimeMillis;
    private long eintragDatumMillis;

    private SimpleDateFormat simpleDateFormatDatum = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
    private SimpleDateFormat simpleDateFormatUhrzeit = new SimpleDateFormat("HH:mm", Locale.GERMAN);


    String uhrzeitUndSekunde;

    //private SharedPreferences sharedPreferences;
    //private SharedPreferences.Editor editor;

    private String automatischOderManuell = "automatisch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

       //sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
       //editor=sharedPreferences.edit();
       //editor.putString("lolo","what the fuck");
       //editor.putString("uhrzeit","13:13");
       //editor.apply();

        //Wichtig zum Reden!!!
        TTS.init(getApplicationContext());


        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        npPriority = findViewById(R.id.npPriority);
        etBlutzucker = findViewById(R.id.etBlutzucker);
        etBe = findViewById(R.id.etBe);
        etBolus = findViewById(R.id.etBolus);
        etKorrektur = findViewById(R.id.etKorrektur);
        etBasal = findViewById(R.id.etBasal);
        tvDatum = findViewById(R.id.tvDatum);
        tvUhrzeit = findViewById(R.id.tvUhrzeit);

        npPriority.setMinValue(1);
        npPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);


        Intent intent = getIntent();

        //Eintrag wird modifiziert
        if (intent.hasExtra(EXTRA_ID)) {
            automatischOderManuell="manuell";
            TTS.speak("intent manuell");
            setTitle("Edit Note");

            etTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            npPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
            etBlutzucker.setText(String.valueOf(intent.getIntExtra(EXTRA_BLUTZUCKER, 0)));
            etBe.setText(String.valueOf(intent.getFloatExtra(EXTRA_BE, 0)));
            etBolus.setText(String.valueOf(intent.getFloatExtra(EXTRA_BOLUS, 0)));
            etKorrektur.setText(String.valueOf(intent.getFloatExtra(EXTRA_KORREKTUR, 0)));
            etBasal.setText(String.valueOf(intent.getFloatExtra(EXTRA_BASAL, 0)));
            tvDatum.setText(intent.getStringExtra(EXTRA_DATUM));
            tvUhrzeit.setText(intent.getStringExtra(EXTRA_UHRZEIT));

            currentTimeMillis=intent.getLongExtra(EXTRA_CURRENT_TIME_MILLIS,0);
            eintragDatumMillis=intent.getLongExtra(EXTRA_EINTRAG_DATUM_MILLIS,0);
        }
        //ENDE Eintrag wird modifiziert

        else {
            //Neuer Eintrag
            automatischOderManuell="automatisch";
            TTS.speak("intent automatisch");
            setTitle("Neuer Eintrag");

            datum = simpleDateFormatDatum.format(new Date());
            uhrzeit = simpleDateFormatUhrzeit.format(new Date());

            tvDatum.setText(datum);
            tvUhrzeit.setText(uhrzeit);

        }
        //ENDE Neuer Eintrag
    }
    //ENDE onCreate


    //Eintrag speichern
    private void saveEintragDiabetes() {
        int blutzucker;
        float be;
        float bolus;
        float korrektur;
        float basal;

        if (etBlutzucker.getText().toString().trim().isEmpty()) {
            blutzucker = 0;
        } else {
            blutzucker = Integer.parseInt(etBlutzucker.getText().toString());
        }
        TTS.speak("zucker"+blutzucker);

        if (etBe.getText().toString().trim().isEmpty()) {
            be = 0;
        } else {
            be = Float.parseFloat(etBe.getText().toString());
        }
        TTS.speak("b e"+be);
        if (etBolus.getText().toString().isEmpty()) {
            bolus = 0;
        } else {
            bolus = Float.parseFloat(etBolus.getText().toString());
        }
        TTS.speak("bolus"+bolus);
        if (etKorrektur.getText().toString().isEmpty()) {
            korrektur = 0;
        } else {
            korrektur = Float.parseFloat(etKorrektur.getText().toString());
        }
        TTS.speak("korrektur"+korrektur);
        if (etBasal.getText().toString().isEmpty()) {
            basal = 0;
        } else {
            basal = Float.parseFloat(etBasal.getText().toString());
        }
        TTS.speak("basal"+basal);

        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        int priority = npPriority.getValue();
        datum = tvDatum.getText().toString();
        uhrzeit = tvUhrzeit.getText().toString();
        //TTS.speak("die uhr sagt"+uhrzeit);
        //editor.putString("uhrzeit", uhrzeit);
        //editor.apply();




        /*
        //Überprüfen, ob Textfelder leer
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        */


        Intent data = new Intent();

        //Datum, Uhrzeit, currentTimeMillis, eintragDatumMillis ermitteln
        simpleDateFormatDatum = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        simpleDateFormatUhrzeit = new SimpleDateFormat("HH:mm", Locale.GERMAN);

        //Datum und Uhrzeit wurden nicht verändert: AUTOMATISCH)
        if (automatischOderManuell.equals("automatisch")) {
            TTS.speak("das ist gut. automatic");
            datum = simpleDateFormatDatum.format(new Date());
            uhrzeit = simpleDateFormatUhrzeit.format(new Date());
            currentTimeMillis = System.currentTimeMillis();
            //aufpasser=0;  todo
        }


        //Datum und-oder Uhrzeit wurden verändert (MANUELL)
        else {
            //automatischOderManuell = "manuell";


            datum = tvDatum.getText().toString();
            uhrzeit = tvUhrzeit.getText().toString();
            uhrzeitUndSekunde=uhrzeit+":00";

            String datumUndUhrzeit = datum + "-" + uhrzeitUndSekunde;


           SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor=sharedPreferences.edit();
            editor.putString("lolo","what what what");
            editor.putString("uhrzeit",uhrzeit);
            editor.apply();


            //editor.putString("uhrzeit",uhrzeit);
            //editor.apply();
            SimpleDateFormat duu = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss", Locale.GERMAN);
            try {
                Date startDate = duu.parse(datumUndUhrzeit);
                currentTimeMillis = startDate != null ? startDate.getTime() : 0;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //ENDE  Datum und-oder Uhrzeit wurden verändert (MANUELL)


        Date startDate;
        try {
            startDate = simpleDateFormatDatum.parse(datum);
            eintragDatumMillis = startDate != null ? startDate.getTime() : 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Datum, Uhrzeit, currentTimeMillis, eintragDatumMillis ermitteln


        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(EXTRA_BLUTZUCKER, blutzucker);
        data.putExtra(EXTRA_BE, be);
        data.putExtra(EXTRA_BOLUS, bolus);
        data.putExtra(EXTRA_KORREKTUR, korrektur);
        data.putExtra(EXTRA_BASAL, basal);

        data.putExtra(EXTRA_DATUM, datum);
        data.putExtra(EXTRA_UHRZEIT, uhrzeit);
        data.putExtra(EXTRA_CURRENT_TIME_MILLIS, currentTimeMillis);
        data.putExtra(EXTRA_EINTRAG_DATUM_MILLIS, eintragDatumMillis);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                //automatischOderManuell="automatisch";
                //editor = sharedPreferences.edit();
                //editor.putString("automatischOderManuell",automatischOderManuell);
                //editor.putString("uhrzeit",uhrzeit);
                //editor.apply();
                saveEintragDiabetes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //DATUM BUTTON
    public void DatumEingeben(View view) {
        DialogFragment datePicker = new DatumFragment();

       //automatischOderManuell = "manuell";
       SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
       SharedPreferences.Editor editor;
       editor=sharedPreferences.edit();

       datum=tvDatum.getText().toString();
        uhrzeit=tvUhrzeit.getText().toString();
       editor.putString("datum",datum);

        TTS.speak("date 1 is"+datum);
        editor.apply();
       //editor.putString("automatischOderManuell", automatischOderManuell);
       ////editor.putString("datum", datum);
       ////editor.putString("uhrzeit", uhrzeit);
       //editor.apply();

       datePicker.show(getSupportFragmentManager(), "date picker");
    }

    //UHRZEIT BUTTON
    public void UhrzeitEingeben(View view) {
        DialogFragment timePicker = new ZeitFragment();
        TTS.speak("time picker");
        //automatischOderManuell = "manuell";
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor=sharedPreferences.edit();
        //editor.putString("automatischOderManuell", automatischOderManuell);
        //editor.putString("datum", datum);
        datum=tvDatum.getText().toString();
        uhrzeit=tvUhrzeit.getText().toString();
        //currentTimeMillis=dat
        editor.putString("datum", datum);
        editor.putString("uhrzeit", uhrzeit);
        TTS.speak("clock button is"+uhrzeit);
        editor.apply();

        //automatischOderManuell = "manuell";
        //editor = sharedPreferences.edit();
        //editor.putString("automatischOderManuell", automatischOderManuell);
        //editor.putString("datum", datum);
        //editor.putString("uhrzeit", uhrzeit);
        //editor.apply();

        timePicker.show(getSupportFragmentManager(), "time picker");
    }
    //Daten Aus DatumFragment erhalten und verarbeiten
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);


        //Aktuelles Datum
        //datum = DateFormat.getDateInstance().format(c.getTime());

        datum=dayOfMonth+"."+(month+1)+"."+year;


        String datumLang = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());


        //TTS.speak(datumLang);
        tvDatum.setText(datum);
        //aufpasser = 1;


    }

    //Daten Aus ZeitFragment erhalten und verarbeiten
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String minuteString;
        String stundeString;

        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = "" + minute;
        }
        if (hourOfDay < 10) {
            stundeString = "0" + hourOfDay;
        } else {
            stundeString = "" + hourOfDay;
        }
        uhrzeitUndSekunde = stundeString + ":" + minuteString + ":00";


        //Aktuelle Uhrzeit
        uhrzeit = stundeString + ":" + minuteString;


        //String uhrzeitKurz=DateFormat.getDateInstance().format(c.getTime());
        //String uhrzeit=DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        //TTS.speak(hourOfDay + "uhr und" + minute + "minuten");
        tvUhrzeit.setText(uhrzeit);
        //aufpasser = 1;
    }


}
