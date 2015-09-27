package br.com.bandtec.saude.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import br.com.bandtec.saude.Adapters.DiarioAdapter;
import br.com.bandtec.saude.Models.DiarioBordo;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Utils.SystemURL;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaAgendaFragment extends Fragment
{

    public TelaAgendaFragment()
    {
    }

    public static TelaAgendaFragment newInstance()
    {
        return new TelaAgendaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.fragment_tela_agenda, container, false);
        final ListView listViewDiarios = (ListView)v.findViewById(R.id.listViewDiarios);
        Button buttonExames = (Button)v.findViewById(R.id.buttonRegistrarPAciente);
        buttonExames.setOnClickListener(new View.OnClickListener()
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

        RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
        {
            @Override
            public void onRequisitionEnd(String json, int status, Exception e)
            {
                DiarioBordo[] d = new Gson().fromJson(json, DiarioBordo[].class);

                listViewDiarios.setAdapter(new DiarioAdapter(getActivity(), d));
            }
        }, SystemURL.URL + "DiarioBordo", "get", null, getActivity());

        return v;
    }
}
