package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.karstenfischerroom.room.roomdatabasediabetestest.TTS;

import java.util.Calendar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatumFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MyPref", 0);
        //String automatischOderManuell = sharedPreferences.getString("automatischOderManuell", "automatisch");
        String datum=sharedPreferences.getString("datum", "00.00.0000");

        TTS.speak("date 2 is"+datum);

        assert datum != null;
        String datumSeparated[]=datum.split("\\.");

        int year = Integer.parseInt(datumSeparated[2]);
        TTS.speak("year"+datumSeparated[2]);
        int month = (Integer.parseInt(datumSeparated[1]));
        TTS.speak("month"+datumSeparated[1]);
        int day = (Integer.parseInt(datumSeparated[0]));
        TTS.speak("day"+datumSeparated[0]);

        Toast.makeText(this.getActivity(), "1  "+year, Toast.LENGTH_SHORT).show();
        Toast.makeText(this.getActivity(), "1  "+month, Toast.LENGTH_SHORT).show();
        Toast.makeText(this.getActivity(), "1  "+day, Toast.LENGTH_SHORT).show();

      int  year1=year;
        int  month1=month-1;
        int  day1=day;
     // Calendar c=Calendar.getInstance();
     //int  year = c.get(Calendar.YEAR);
     //int  month = c.get(Calendar.MONTH);
     // int day = c.get(Calendar.DAY_OF_MONTH);

     //  TTS.speak("jahr"+year);
     //  TTS.speak("monat"+month);
     //  TTS.speak("tag"+day);

     //  Toast.makeText(this.getActivity(), "2  "+year, Toast.LENGTH_SHORT).show();
     //  Toast.makeText(this.getActivity(), "2  "+month, Toast.LENGTH_SHORT).show();
     //  Toast.makeText(this.getActivity(), "2  "+day, Toast.LENGTH_SHORT).show();

        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year1,month1,day1);
    }
}
