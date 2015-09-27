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

import br.com.bandtec.saude.Models.Pressao;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Util.Session;
import br.com.bandtec.saude.Utils.ShowMessage;
import br.com.bandtec.saude.Utils.SystemURL;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaPressaoFragment extends Fragment
{

    public TelaPressaoFragment()
    {
    }

    public static TelaPressaoFragment newInstance()
    {
        return new TelaPressaoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.fragment_tela_pressao, container, false);

        final EditText editTextPressaoSis = (EditText)v.findViewById(R.id.editTextPressaoSis);
        final EditText editTextPressaoDias = (EditText)v.findViewById(R.id.editTextPressaoDias);
        Button buttonCancelar = (Button)v.findViewById(R.id.buttonCancelar);
        Button buttonSalvar = (Button)v.findViewById(R.id.buttonSalvar);

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
                Pressao p = new Pressao();

                Calendar c = Calendar.getInstance();
                p.setIdPaciente(Session.ID);
                Date d = c.getTime();

                p.setData(new SimpleDateFormat("yyyy-mm-dd").format(d));

                p.setSistolica(Integer.parseInt(editTextPressaoSis.getText().toString()));
                p.setDiastolica(Integer.parseInt(editTextPressaoDias.getText().toString()));

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
                }, SystemURL.URL + "pressaos", "post", p, getActivity());
            }
        });

        return v;
    }
}
