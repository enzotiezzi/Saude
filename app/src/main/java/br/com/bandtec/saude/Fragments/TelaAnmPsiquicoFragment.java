package br.com.bandtec.saude.Fragments;

        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;

        import br.com.bandtec.saude.Models.AnaminesiaFisica;
        import br.com.bandtec.saude.Models.PacienteAnaminesiaPsiquica;
        import br.com.bandtec.saude.R;
        import br.com.bandtec.saude.Requisition.RequisitionTask;
        import br.com.bandtec.saude.Util.Session;
        import br.com.bandtec.saude.Utils.ShowMessage;
        import br.com.bandtec.saude.Utils.SystemURL;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaAnmPsiquicoFragment extends Fragment
{
    private View v;

    public TelaAnmPsiquicoFragment()
    {
    }

    public static TelaAnmPsiquicoFragment newInstance()
    {
        return new TelaAnmPsiquicoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v =  inflater.inflate(R.layout.fragment_tela_anm_fisica, container, false);

        Button buttonSalvar = (Button)v.findViewById(R.id.buttonSalvar);
        Button buttonCancelar = (Button)v.findViewById(R.id.buttonCancelar);

        for (int i = 0 ; i < 4 ; i++)
        {
            PacienteAnaminesiaPsiquica a = new PacienteAnaminesiaPsiquica();
            a.setValor(true);

            psiquicos.add(a);
        }

        Calendar c = Calendar.getInstance();
        Date d = c.getTime();

        psiquicos.get(0).setNome("Temperamento agressivo ?");
        psiquicos.get(0).setIdPaciente(Session.ID);
        psiquicos.get(0).setData(new SimpleDateFormat("yyyy-mm-dd").format(d));

        psiquicos.get(1).setNome("Irritabilidade fácil ?");
        psiquicos.get(1).setIdPaciente(Session.ID);
        psiquicos.get(1).setData(new SimpleDateFormat("yyyy-mm-dd").format(d));

        psiquicos.get(2).setNome("Indisposição ?");
        psiquicos.get(2).setIdPaciente(Session.ID);
        psiquicos.get(2).setData(new SimpleDateFormat("yyyy-mm-dd").format(d));

        psiquicos.get(3).setNome("Associabilidade regular ?");
        psiquicos.get(3).setIdPaciente(Session.ID);
        psiquicos.get(3).setData(new SimpleDateFormat("yyyy-mm-dd").format(d));

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
                for (int i = 0; i < 4; i++)
                {
                    RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
                    {
                        @Override
                        public void onRequisitionEnd(String json, int status, Exception e)
                        {
                        }
                    }, SystemURL.URL + "PacienteAnaminesiaPsiquicas", "post", psiquicos.get(i), getActivity());
                }

                ShowMessage.showToast(getActivity(), "Gravado com sucesso");
            }
        });


        return v;
    }

    List<PacienteAnaminesiaPsiquica> psiquicos = new ArrayList<>();

    RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            RadioButton radio = (RadioButton)v.findViewById(checkedId);

            switch (group.getId())
            {
                case R.id.radioGroup1:
                    psiquicos.get(0).setValor(radio.isChecked());
                    break;
                case R.id.radioGroup2:
                    psiquicos.get(1).setValor(radio.isChecked());
                    break;
                case R.id.radioGroup3:
                    psiquicos.get(2).setValor(radio.isChecked());
                    break;
                case R.id.radioGroup4:
                    psiquicos.get(3).setValor(radio.isChecked());
                    break;
            }
        }
    };
}
