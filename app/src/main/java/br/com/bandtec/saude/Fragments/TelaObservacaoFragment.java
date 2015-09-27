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

import br.com.bandtec.saude.Models.Observacao;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Util.Session;
import br.com.bandtec.saude.Utils.ShowMessage;
import br.com.bandtec.saude.Utils.SystemURL;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaObservacaoFragment extends Fragment
{

    public TelaObservacaoFragment()
    {
    }

    public static TelaObservacaoFragment newInstace()
    {
        return new TelaObservacaoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_tela_observacao, container, false);

        Button buttonSalvar = (Button)v.findViewById(R.id.buttonSalvar);
        Button buttonCancelar = (Button)v.findViewById(R.id.buttonCancelar);
        final EditText editTextObs = (EditText)v.findViewById(R.id.editTextObservacao);

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
                Observacao obs = new Observacao();

                Calendar c = Calendar.getInstance();
                Date d = c.getTime();

                obs.setIdPaciente(Session.ID);
                obs.setData(new SimpleDateFormat("yyyy-mm-dd").format(d));
                obs.setTexto(editTextObs.getText().toString());

                RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
                {
                    @Override
                    public void onRequisitionEnd(String json, int status, Exception e)
                    {
                        if (e == null)
                        {
                            if (json != null)
                            {
                                ShowMessage.showToast(getActivity(), "Observação registrada com sucesso");
                            }
                        }
                    }
                }, SystemURL.URL + "observacaes", "post", obs, getActivity());
            }
        });

        return v;
    }
}
