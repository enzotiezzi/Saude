package br.com.bandtec.saude.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Views.TelaAnmPsiquico;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaDiarioFragment extends Fragment
{

    public TelaDiarioFragment()
    {
    }

    public static TelaDiarioFragment newInstance()
    {
        return new TelaDiarioFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_tela_diario, container, false);

        ImageView imageViewTemp = (ImageView)view.findViewById(R.id.imageViewTemperatura);
        ImageView imageViewPressao = (ImageView)view.findViewById(R.id.imageView3);
        ImageView imageViewRemedio = (ImageView)view.findViewById(R.id.imageView4);
        ImageView imageViewFisica = (ImageView)view.findViewById(R.id.imageView5);
        ImageView imageViewPsiquica = (ImageView)view.findViewById(R.id.imageView6);
        ImageView imageViewObservacao = (ImageView)view.findViewById(R.id.imageView7);

        imageViewTemp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaTemperaturaFragment.newInstance())
                        .commit();
            }
        });

        imageViewPressao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaPressaoFragment.newInstance())
                        .commit();
            }
        });

        imageViewRemedio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaRemedioFragment.newInstance())
                        .commit();
            }
        });

        imageViewFisica.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaAnmFisicaFragment.newInstance())
                        .commit();
            }
        });

        imageViewPsiquica.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaAnmPsiquicoFragment.newInstance())
                        .commit();
            }
        });

        imageViewObservacao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaObservacaoFragment.newInstace())
                        .commit();
            }
        });

        return view;
    }
}
