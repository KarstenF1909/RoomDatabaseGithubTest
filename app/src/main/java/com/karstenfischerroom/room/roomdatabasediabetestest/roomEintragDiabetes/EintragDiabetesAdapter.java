package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karstenfischerroom.room.roomdatabasediabetestest.R;

public class EintragDiabetesAdapter extends ListAdapter<Note, EintragDiabetesAdapter.NoteHolder> {

    //private List<Note> notes=new ArrayList<>();   nicht mehr wichtig
    private OnItemClickListener listener;

    public EintragDiabetesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note note, @NonNull Note t1) {
            return note.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note note, @NonNull Note t1) {
            return note.getTitle().equals(t1.getTitle()) &&
                    note.getDescription().equals(t1.getDescription()) &&
                    note.getPriority() == t1.getPriority() &&
                    note.getBlutzucker() == t1.getBlutzucker() &&
                    note.getBe() == t1.getBe() &&
                    note.getBolus() == t1.getBolus() &&
                    note.getKorrektur() == t1.getKorrektur() &&
                    note.getBasal() == t1.getBasal() &&
                    note.getDatum().equals(t1.getDatum()) &&
                    note.getUhrzeit().equals(t1.getUhrzeit()) &&
                    note.getCurrentTimeMillis() == t1.getCurrentTimeMillis() &&
                    note.getEintragDatumMillis() == t1.getEintragDatumMillis();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, viewGroup, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {




        Note currentNote = getItem(position);

        if(currentNote.getBlutzucker()>150){
            //noteHolder.tvBlutzuckerWert.setTextColor(Color.MAGENTA);
            //noteHolder.tvBlutzuckerWert.setTextColor(Color.rgb(100,62,24));
            noteHolder.tvBlutzuckerWert.setTextColor(Color.argb(100,255,158,61));
        }
        if(currentNote.getBlutzucker()>300){
            //noteHolder.tvBlutzuckerWert.setTextColor(Color.MAGENTA);
            //noteHolder.tvBlutzuckerWert.setTextColor(Color.rgb(100,62,24));
            noteHolder.tvBlutzuckerWert.setTextColor(Color.argb(100,255,0,0));
        }
        if(currentNote.getBlutzucker()<150){

            //noteHolder.tvBlutzuckerWert.setTextColor(Color.GREEN);
            noteHolder.tvBlutzuckerWert.setTextColor(Color.rgb(116,255,61));
        }




        noteHolder.tvPriority.setText(String.valueOf(currentNote.getPriority()));

        //noteHolder.tvBlutzuckerWert.setText(String.valueOf(currentNote.getBlutzucker()));
        noteHolder.tvBe.setText(String.valueOf(currentNote.getBe()));
        noteHolder.tvBolus.setText(String.valueOf(currentNote.getBolus()));
        noteHolder.tvKorrektur.setText(String.valueOf(currentNote.getKorrektur()));
        noteHolder.tvBasal.setText(String.valueOf(currentNote.getBasal()));
        noteHolder.tvDatum.setText(String.valueOf(currentNote.getDatum()));
        noteHolder.tvUhrzeit.setText(String.valueOf(currentNote.getUhrzeit()));
        noteHolder.tvCurrentTimeMillis.setText(String.valueOf(currentNote.getCurrentTimeMillis()));
        noteHolder.tvEintragDatumMillis.setText(String.valueOf(currentNote.getEintragDatumMillis()));

        //SpannableStringBuilder builder = new SpannableStringBuilder();
//
        //SpannableString str1= new SpannableString("Blutzucker  ");
        //str1.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, str1.length(), 0);
        //str1.setSpan(new ForegroundColorSpan(Color.GREEN), 0, str1.length(), 0);
        //builder.append(str1);



       // SpannableString str2= new SpannableString(String.valueOf(currentNote.getBlutzucker()));
//
       // if(currentNote.getBlutzucker()>150){
       //     str2.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, str2.length(), 0);
//
       // }
       // else{
       //     str2.setSpan(new ForegroundColorSpan(Color.GREEN), 0, str2.length(), 0);
//
       // }
//
//
//
       // builder.append(str2);
//
      //
       // tv.setText( builder, TextView.BufferType.SPANNABLE);



        noteHolder.tvBlutzucker.setText("Blutzucker: ");
        noteHolder.tvBlutzuckerWert.setText(String.format("%s", String.valueOf(currentNote.getBlutzucker())));

        //  noteHolder.tvBlutzucker.setText( builder, TextView.BufferType.SPANNABLE);


        noteHolder.tvDescription.setText(currentNote.getDescription());





        if(!currentNote.getTitle().trim().isEmpty()){
            noteHolder.tvTitle.setText(currentNote.getTitle());
        }
        else {
            noteHolder.tvTitle.setVisibility(View.GONE);
        }
        if(!currentNote.getDescription().trim().isEmpty()){
            noteHolder.tvDescription.setText(currentNote.getDescription());
        }
        else {
            noteHolder.tvDescription.setVisibility(View.GONE);
        }


        if(currentNote.getBe()!=0){
            noteHolder.tvBe.setText("Broteinheiten - "+String.valueOf(currentNote.getBe()));
        }
        else {
            noteHolder.tvBe.setVisibility(View.GONE);
        }


        if(currentNote.getBolus()!=0){
            noteHolder.tvBolus.setText("Bolus - "+String.valueOf(currentNote.getBolus()));
        }
        else {
            noteHolder.tvBolus.setVisibility(View.GONE);
        }



        if(currentNote.getKorrektur()!=0){
            noteHolder.tvKorrektur.setText("Korrektur - "+String.valueOf(currentNote.getKorrektur()));
        }
        else {
            noteHolder.tvKorrektur.setVisibility(View.GONE);
        }



        if(currentNote.getBasal()!=0){
            noteHolder.tvBasal.setText("Basal - "+String.valueOf(currentNote.getBasal()));
        }
        else {
            noteHolder.tvBasal.setVisibility(View.GONE);
        }









        noteHolder.tvDatum.setText(String.valueOf(currentNote.getDatum()));
        noteHolder.tvUhrzeit.setText(String.valueOf(currentNote.getUhrzeit()));
        noteHolder.tvCurrentTimeMillis.setText(String.valueOf(currentNote.getCurrentTimeMillis()));
        noteHolder.tvEintragDatumMillis.setText(String.valueOf(currentNote.getEintragDatumMillis()));









    }


    //Für onSwipe
    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPriority;
        private TextView tvBlutzucker;
        private TextView tvBlutzuckerWert;
        private TextView tvBe;
        private TextView tvBolus;
        private TextView tvKorrektur;
        private TextView tvBasal;

        private TextView tvDatum;
        private TextView tvUhrzeit;
        private TextView tvCurrentTimeMillis;
        private TextView tvEintragDatumMillis;








        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvBlutzucker = itemView.findViewById(R.id.tvBlutzucker);
            tvBlutzuckerWert = itemView.findViewById(R.id.tvBlutzuckerWert);
            tvBe = itemView.findViewById(R.id.tvBe);
            tvBolus = itemView.findViewById(R.id.tvBolus);
            tvKorrektur = itemView.findViewById(R.id.tvKorrektur);
            tvBasal = itemView.findViewById(R.id.tvBasal);


            tvDatum = itemView.findViewById(R.id.tvDatum);
            tvUhrzeit = itemView.findViewById(R.id.tvUhrzeit);
            tvCurrentTimeMillis=itemView.findViewById(R.id.tvCurrentTimeMillis);
            tvEintragDatumMillis=itemView.findViewById(R.id.tvEintragDatumMillis);





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }

                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
