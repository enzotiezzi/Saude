package br.com.bandtec.saude.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

import br.com.bandtec.saude.Receiver.AlarmeReceiver;

/**
 * Created by enzo on 26/09/2015.
 */
public class Alarme
{
    public static void registraAlarme(Context context, Date data, int periodicidade)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data.getTime());

        Intent i = new Intent(context, AlarmeReceiver.class);
        i.putExtra("periodicidade", periodicidade);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i,PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        ShowMessage.showDialog(context, "Alarme confirmado", "Alarme registrado com suceso");
    }
}
