package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.karstenfischerroom.room.roomdatabasediabetestest.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEditEintragDiabetesActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_TITLE";
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

    public static final String EXTRA_EINTRAG_DATUM_MILLIS =
            "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_EINTRAG_DATUM_MILLIS";


    private EditText etTitle;
    private EditText etDescription;
    private NumberPicker npPriority;
    private EditText etBlutzucker;
    private EditText etBe;
    private EditText etBolus;
    private EditText etKorrektur;
    private EditText etBasal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        npPriority = findViewById(R.id.npPriority);
        etBlutzucker = findViewById(R.id.etBlutzucker);
        etBe = findViewById(R.id.etBe);
        etBolus = findViewById(R.id.etBolus);
        etKorrektur = findViewById(R.id.etKorrektur);
        etBasal = findViewById(R.id.etBasal);


        npPriority.setMinValue(1);
        npPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit EintragDiabetes");


            etTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            npPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
            etBe.setText(intent.getStringExtra(EXTRA_BE));
            etBolus.setText(intent.getStringExtra(EXTRA_BOLUS));
            etKorrektur.setText(intent.getStringExtra(EXTRA_KORREKTUR));
            etBasal.setText(intent.getStringExtra(EXTRA_BASAL));


        } else {
            setTitle("Add EintragDiabetes");
        }


    }

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

        if (etBe.getText().toString().trim().isEmpty()) {
            be = 0;
        } else {
            be = Float.parseFloat(etBe.getText().toString());
        }
        if (etBolus.getText().toString().isEmpty()) {
            bolus = 0;
        } else {
            bolus = Float.parseFloat(etBolus.getText().toString());
        }

        if (etKorrektur.getText().toString().isEmpty()) {
            korrektur = 0;
        } else {
            korrektur = Float.parseFloat(etKorrektur.getText().toString());
        }

        if (etBasal.getText().toString().isEmpty()) {
            basal = 0;
        } else {
            basal = Float.parseFloat(etBasal.getText().toString());
        }

        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        int priority = npPriority.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();

        //Datum, Uhrzeit, currentTimeMillis, eintragDatumMillis ermitteln
        SimpleDateFormat simpleDateFormatDatum = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        final String datum = simpleDateFormatDatum.format(new Date());
        SimpleDateFormat simpleDateFormatUhrzeit = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        final String uhrzeit = simpleDateFormatUhrzeit.format(new Date());

        final long currentTimeMillis = System.currentTimeMillis();

        Date startDate = null;
        try {
            startDate = simpleDateFormatDatum.parse(datum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //----------------------     Was nehmen wir als Start Date?  START   ------------------------------
        //     assert startDate != null;
        //     eintragDatumMillisLong = startDate.getTime();
        //     eintragDatumMillisLong = Objects.requireNonNull(startDate).getTime();
        long eintragDatumMillis = startDate != null ? startDate.getTime() : 0;
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
                saveEintragDiabetes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
