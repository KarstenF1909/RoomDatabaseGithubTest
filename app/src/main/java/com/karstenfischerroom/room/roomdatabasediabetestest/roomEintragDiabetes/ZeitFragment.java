package com.karstenfischerroom.room.roomdatabasediabetestest.roomEintragDiabetes;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;

import com.karstenfischerroom.room.roomdatabasediabetestest.TTS;

import java.util.Calendar;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ZeitFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("MyPref", 0);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MyPref", 0);
        String automatischOderManuell = sharedPreferences.getString("automatischOderManuell", "automatisch");


        if(automatischOderManuell.equals("manuell")){
         TTS.speak("Zeitfragment manuell");

            String   uhrzeit= sharedPreferences.getString("uhrzeit", "00:00");

            String lolo=sharedPreferences.getString("lolo", "00:00");
            TTS.speak("lolo"+lolo);

            TTS.speak("uhrzeit"+uhrzeit);
            String[]  uhrzeitSplit=uhrzeit.split(":");

            int hour= Integer.parseInt(uhrzeitSplit[0]);
            TTS.speak("stunde"+hour);

            int minute= Integer.parseInt(uhrzeitSplit[1]);
            TTS.speak("minute"+minute);
            return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),hour,minute,DateFormat.is24HourFormat(getActivity()));

        }
        else{
            TTS.speak("Zeitfragment automatisch");
            Calendar c=Calendar.getInstance();
            int hour=c.get(Calendar.HOUR_OF_DAY);
            int minute=c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),hour,minute,DateFormat.is24HourFormat(getActivity()));

        }






    }
}
