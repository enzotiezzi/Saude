package br.com.bandtec.saude.Views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import java.util.Calendar;
import java.util.Date;

import br.com.bandtec.saude.Models.Remedio;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Utils.Alarme;
import br.com.bandtec.saude.Utils.SystemURL;

public class MainActivity extends ActionBarActivity
{
    private EditText editTextData;
    private EditText editTextHora;
    private EditText editTextPeriodo;
    private Button buttonRegistrar;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(getResources().getColor(R.color.corPrimaria));

        editTextData = (EditText)findViewById(R.id.editTextData);
        editTextHora = (EditText)findViewById(R.id.editTextHora);
        editTextPeriodo = (EditText)findViewById(R.id.editTextPeriodo);
        buttonRegistrar = (Button)findViewById(R.id.buttonSet);
        buttonRegistrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int dia = Integer.parseInt(editTextData.getText().toString().split("/")[0]);
                int mes = Integer.parseInt(editTextData.getText().toString().split("/")[1]);
                int ano = Integer.parseInt(editTextData.getText().toString().split("/")[2]);

                int hora = Integer.parseInt(editTextHora.getText().toString().split(":")[0]);
                int minuto = Integer.parseInt(editTextHora.getText().toString().split(":")[1]);

                int periodicidade = Integer.parseInt(editTextPeriodo.getText().toString());

                Date data;
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes-1);
                calendar.set(Calendar.YEAR, ano);
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minuto);
                calendar.set(Calendar.SECOND, 0);

                data = calendar.getTime();

                Alarme.registraAlarme(context, data, periodicidade);
            }
        });

        RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
        {
            @Override
            public void onRequisitionEnd(String json, int status, Exception e)
            {
            }
        }, SystemURL.URL + "remedios", "GET", null, context);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
