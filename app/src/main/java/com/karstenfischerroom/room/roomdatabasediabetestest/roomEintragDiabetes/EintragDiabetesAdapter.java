package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karstenfischerroom.room.roomdatabasediabetestest.MainActivity;
import com.karstenfischerroom.room.roomdatabasediabetestest.R;

public class EintragDiabetesAdapter extends ListAdapter<EintragDiabetes, EintragDiabetesAdapter.NoteHolder> {

    //private List<EintragDiabetes> notes=new ArrayList<>();   nicht mehr wichtig
    private OnItemClickListener listener;

    public EintragDiabetesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<EintragDiabetes> DIFF_CALLBACK = new DiffUtil.ItemCallback<EintragDiabetes>() {
        @Override
        public boolean areItemsTheSame(@NonNull EintragDiabetes eintragDiabetes, @NonNull EintragDiabetes t1) {
            return eintragDiabetes.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull EintragDiabetes eintragDiabetes, @NonNull EintragDiabetes t1) {
            return eintragDiabetes.getTitle().equals(t1.getTitle()) &&
                    eintragDiabetes.getDescription().equals(t1.getDescription()) &&
                    eintragDiabetes.getPriority() == t1.getPriority() &&
                    eintragDiabetes.getBlutzucker() == t1.getBlutzucker() &&
                    eintragDiabetes.getBe() == t1.getBe();













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
        EintragDiabetes currentEintragDiabetes = getItem(position);
        noteHolder.tvTitle.setText(currentEintragDiabetes.getTitle());
        noteHolder.tvDescription.setText(currentEintragDiabetes.getDescription());
        noteHolder.tvPriority.setText(String.valueOf(currentEintragDiabetes.getPriority()));

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1= new SpannableString("Blutzucker  ");
        //str1.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, str1.length(), 0);
        str1.setSpan(new ForegroundColorSpan(Color.GREEN), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2= new SpannableString(String.valueOf(currentEintragDiabetes.getBlutzucker()));

        if(currentEintragDiabetes.getBlutzucker()>150){
            str2.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, str2.length(), 0);

        }
        else{
            str2.setSpan(new ForegroundColorSpan(Color.GREEN), 0, str2.length(), 0);

        }



        builder.append(str2);

       //
        //tv.setText( builder, TextView.BufferType.SPANNABLE);




        if(currentEintragDiabetes.getBlutzucker()>150){
            //noteHolder.tvBlutzuckerWert.setTextColor(Color.MAGENTA);
            //noteHolder.tvBlutzuckerWert.setTextColor(Color.rgb(100,62,24));
            noteHolder.tvBlutzuckerWert.setTextColor(Color.argb(100,255,158,61));
        }
        else{

            //noteHolder.tvBlutzuckerWert.setTextColor(Color.GREEN);
            noteHolder.tvBlutzuckerWert.setTextColor(Color.rgb(116,255,61));
        }

        noteHolder.tvBlutzucker.setText("Blutzucker: ");
        noteHolder.tvBlutzuckerWert.setText(""+String.valueOf(currentEintragDiabetes.getBlutzucker()));

        //  noteHolder.tvBlutzucker.setText( builder, TextView.BufferType.SPANNABLE);

        if(currentEintragDiabetes.getBe()!=0){
            noteHolder.tvBe.setText("Broteinheiten - "+String.valueOf(currentEintragDiabetes.getBe()));
        }
        else {
            noteHolder.tvBe.setVisibility(View.GONE);
        }


        if(currentEintragDiabetes.getBolus()!=0){
            noteHolder.tvBolus.setText("Bolus - "+String.valueOf(currentEintragDiabetes.getBolus()));
        }
        else {
            noteHolder.tvBolus.setVisibility(View.GONE);
        }



        if(currentEintragDiabetes.getKorrektur()!=0){
            noteHolder.tvKorrektur.setText("Korrektur - "+String.valueOf(currentEintragDiabetes.getKorrektur()));
        }
        else {
            noteHolder.tvKorrektur.setVisibility(View.GONE);
        }



        if(currentEintragDiabetes.getBasal()!=0){
            noteHolder.tvBasal.setText("Basal - "+String.valueOf(currentEintragDiabetes.getBasal()));
        }
        else {
            noteHolder.tvBasal.setVisibility(View.GONE);
        }









        noteHolder.tvDatum.setText(String.valueOf(currentEintragDiabetes.getDatum()));
        noteHolder.tvUhrzeit.setText(String.valueOf(currentEintragDiabetes.getUhrzeit()));
        noteHolder.tvCurrentTimeMillis.setText(String.valueOf(currentEintragDiabetes.getCurrentTimeMillis()));
        noteHolder.tvEintragDatumMillis.setText(String.valueOf(currentEintragDiabetes.getEintragDatumMillis()));









    }


    //Für onSwipe
    public EintragDiabetes getNoteAt(int position) {
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
        void onItemClick(EintragDiabetes eintragDiabetes);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
