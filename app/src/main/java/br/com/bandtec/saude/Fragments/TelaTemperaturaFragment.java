package br.com.bandtec.saude.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.bandtec.saude.R;

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



        return v;
    }
}
