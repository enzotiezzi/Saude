package br.com.bandtec.saude.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.bandtec.saude.Receiver.TelaAlarme;

public class AlarmeReceiver extends BroadcastReceiver
{
    public AlarmeReceiver()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        int periodicidade = intent.getIntExtra("periodicidade", 0);

        Intent i = new Intent(context, TelaAlarme.class);
        i.putExtra("periodicidade", periodicidade);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
