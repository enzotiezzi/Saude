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

import br.com.bandtec.saude.Models.Temperatura;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Util.Session;
import br.com.bandtec.saude.Utils.ShowMessage;
import br.com.bandtec.saude.Utils.SystemURL;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaTemperaturaFragment extends Fragment
{

    public TelaTemperaturaFragment()
    {
    }

    public static TelaTemperaturaFragment newInstance()
    {
        return new TelaTemperaturaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.fragment_tela_temperatur, container, false);

        final EditText editTextTemperatura = (EditText)v.findViewById(R.id.editTextTemperatura);
        Button buttonSalvar = (Button)v.findViewById(R.id.buttonSalvar);
        Button buttonCancelar = (Button)v.findViewById(R.id.buttonCancelar);

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
                Temperatura t = new Temperatura();
                Calendar c = Calendar.getInstance();
                Date d = c.getTime();

                t.setData(new SimpleDateFormat("yyyy-mm-dd").format(d));

                t.setGrau(Float.parseFloat(editTextTemperatura.getText().toString()));
                t.setIdPaciente(Session.ID);

                RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
                {
                    @Override
                    public void onRequisitionEnd(String json, int status, Exception e)
                    {
                        if (json != null)
                        {
                            ShowMessage.showToast(getActivity(), "Registrado com sucesso");
                        }
                    }
                }, SystemURL.URL + "temperaturas", "post", t, getActivity());
            }
        });


        return v;
    }
}
