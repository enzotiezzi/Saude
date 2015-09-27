package br.com.bandtec.saude.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.bandtec.saude.Models.PacienteRemedio;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Util.Session;
import br.com.bandtec.saude.Utils.Alarme;
import br.com.bandtec.saude.Utils.ShowMessage;
import br.com.bandtec.saude.Utils.SystemURL;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaRemedioFragment extends Fragment
{
    private EditText editTextData;
    private EditText editTextHora;
    private EditText editTextPeriodo;
    private Button buttonRegistrar;

    public TelaRemedioFragment()
    {
    }

    public static TelaRemedioFragment newInstance()
    {
        return new TelaRemedioFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.fragment_tela_remedio, container, false);

        final EditText editTextNomeRemedio = (EditText)v.findViewById(R.id.editTextNomeRemedio);
        EditText editTextDosagem = (EditText)v.findViewById(R.id.editTextDosagem);
        Button buttonCancelar = (Button)v.findViewById(R.id.buttonCancelar);
        Button buttonSalvar = (Button)v.findViewById(R.id.buttonSalvar);

        editTextData = (EditText)v.findViewById(R.id.editTextData);
        editTextHora = (EditText)v.findViewById(R.id.editTextHora);
        editTextPeriodo = (EditText)v.findViewById(R.id.editTextPeriodicidade);
        buttonRegistrar = (Button)v.findViewById(R.id.buttonSalvar);

        buttonCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaDiarioFragment.newInstance())
                        .commit();
            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final PacienteRemedio r = new PacienteRemedio();


                Calendar c = Calendar.getInstance();
                r.setIdPaciente(Session.ID);
                Date d = c.getTime();
                r.setIdRemedio(1);
                r.setData(new SimpleDateFormat("yyyy-mm-dd").format(d));
                r.setNome(editTextNomeRemedio.getText().toString());

                int dia = Integer.parseInt(editTextData.getText().toString().split("/")[0]);
                int mes = Integer.parseInt(editTextData.getText().toString().split("/")[1]);
                int ano = Integer.parseInt(editTextData.getText().toString().split("/")[2]);

                int hora = Integer.parseInt(editTextHora.getText().toString().split(":")[0]);
                int minuto = Integer.parseInt(editTextHora.getText().toString().split(":")[1]);

                int periodicidade = Integer.parseInt(editTextPeriodo.getText().toString());

                Date data;
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes - 1);
                calendar.set(Calendar.YEAR, ano);
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minuto);
                calendar.set(Calendar.SECOND, 0);

                data = calendar.getTime();

                Alarme.registraAlarme(getActivity(), data, periodicidade);

                RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
                {
                    @Override
                    public void onRequisitionEnd(String json, int status, Exception e)
                    {
                        if (json != null)
                        {
                            ShowMessage.showToast(getActivity(), "Pressão registrada com sucesso");
                        }
                    }
                }, SystemURL.URL + "remedios", "post", r, getActivity());
            }
        });

        return v;
    }
}
