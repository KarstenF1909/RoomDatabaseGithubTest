package com.karstenfischerroom.room.roomdatabasediabetestest;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes.AddEditEintragDiabetesActivity;
import com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes.EintragDiabetes;
import com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes.EintragDiabetesAdapter;
import com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes.EintragDiabetesViewModel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST=1;
    public static final int EDIT_NOTE_REQUEST=2;
    private EintragDiabetesViewModel eintragDiabetesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAdd=findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddEditEintragDiabetesActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final EintragDiabetesAdapter adapter=new EintragDiabetesAdapter();
        recyclerView.setAdapter(adapter);

        eintragDiabetesViewModel =ViewModelProviders.of(this).get(EintragDiabetesViewModel.class);
        eintragDiabetesViewModel.getAllEintragDiabetes().observe(this, new Observer<List<EintragDiabetes>>() {
            @Override
            public void onChanged(@Nullable List<EintragDiabetes> eintragDiabetes) {
                //update Recyclerview
                adapter.submitList(eintragDiabetes);

                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
               eintragDiabetesViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Gelöscht", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new EintragDiabetesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EintragDiabetes eintragDiabetes) {
                Intent intent=new Intent(MainActivity.this,AddEditEintragDiabetesActivity.class);
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_ID, eintragDiabetes.getId());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_TITLE, eintragDiabetes.getTitle());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_DESCRIPTION, eintragDiabetes.getDescription());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_PRIORITY, eintragDiabetes.getPriority());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_BLUTZUCKER, eintragDiabetes.getBlutzucker());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_BE, eintragDiabetes.getBe());

                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_DATUM, eintragDiabetes.getDatum());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_UHRZEIT, eintragDiabetes.getUhrzeit());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_CURRENT_TIME_MILLIS, eintragDiabetes.getCurrentTimeMillis());
                intent.putExtra(AddEditEintragDiabetesActivity.EXTRA_EINTRAG_DATUM_MILLIS, eintragDiabetes.getEintragDatumMillis());



                startActivityForResult(intent,EDIT_NOTE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_NOTE_REQUEST&&resultCode==RESULT_OK){
            String title=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_TITLE);
            String description=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_DESCRIPTION);
            int priority=data.getIntExtra(AddEditEintragDiabetesActivity.EXTRA_PRIORITY,1);

            int blutzucker=data.getIntExtra(AddEditEintragDiabetesActivity.EXTRA_BLUTZUCKER,100);
            float be=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_BE,1);
            float bolus=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_BOLUS,1);
            float korrektur=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_KORREKTUR,1);
            float basal=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_BASAL,1);

            String datum=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_DATUM);
            String uhrzeit=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_UHRZEIT);
            long currentTimeMillis=data.getLongExtra(AddEditEintragDiabetesActivity.EXTRA_CURRENT_TIME_MILLIS,100);
            long eintragDatumMillis=data.getLongExtra(AddEditEintragDiabetesActivity.EXTRA_EINTRAG_DATUM_MILLIS,100);









            EintragDiabetes eintragDiabetes =new EintragDiabetes(title,description,priority,blutzucker,be,bolus,korrektur,basal,datum,uhrzeit,currentTimeMillis,eintragDatumMillis);
            eintragDiabetesViewModel.insert(eintragDiabetes);
            Toast.makeText(this, "EintragDiabetes saved", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==EDIT_NOTE_REQUEST&&resultCode==RESULT_OK){
            int id=data.getIntExtra(AddEditEintragDiabetesActivity.EXTRA_ID,-1);

            if(id==-1){
                Toast.makeText(this, "EintragDiabetes can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String title=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_TITLE);
            String description=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_DESCRIPTION);
            int priority=data.getIntExtra(AddEditEintragDiabetesActivity.EXTRA_PRIORITY,1);
            int blutzucker=data.getIntExtra(AddEditEintragDiabetesActivity.EXTRA_BLUTZUCKER,100);
            float be=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_BE,1);
            float bolus=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_BOLUS,1);
            float korrektur=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_KORREKTUR,1);
            float basal=data.getFloatExtra(AddEditEintragDiabetesActivity.EXTRA_BASAL,1);

            String datum=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_DATUM);
            String uhrzeit=data.getStringExtra(AddEditEintragDiabetesActivity.EXTRA_UHRZEIT);
            long currentTimeMillis=data.getLongExtra(AddEditEintragDiabetesActivity.EXTRA_CURRENT_TIME_MILLIS,100);
            long eintragDatumMillis=data.getLongExtra(AddEditEintragDiabetesActivity.EXTRA_EINTRAG_DATUM_MILLIS,100);






            EintragDiabetes eintragDiabetes =new EintragDiabetes(title,description,priority,blutzucker,be,bolus,korrektur,basal,datum,uhrzeit,currentTimeMillis,eintragDatumMillis);
            eintragDiabetes.setId(id);
            eintragDiabetesViewModel.update(eintragDiabetes);
            Toast.makeText(this, "EintragDiabetes updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Nothing saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_notes:
                eintragDiabetesViewModel.deleteAllNotes();
                Toast.makeText(this, "Alles gelöscht", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
