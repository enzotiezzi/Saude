package br.com.bandtec.saude.Fragments;

import android.content.Context;
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

import javax.sql.CommonDataSource;

import br.com.bandtec.saude.Models.Paciente;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Utils.ShowMessage;
import br.com.bandtec.saude.Utils.SystemURL;
import br.com.bandtec.saude.Views.TelaCandidato;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaNovoPacienteFragment extends Fragment
{
    private EditText editTextNome;
    private EditText editTextCPF;
    private EditText editTextSexo;
    private EditText editTextNascimento;
    private EditText editTextDiagnostico;
    private EditText editTextResponsavel;
    private EditText editTextContato;
    private Button buttonSalvar;
    private Button buttonCancelar;
    private View v;

    public TelaNovoPacienteFragment()
    {
    }

    public static TelaNovoPacienteFragment newInstance()
    {
        return new TelaNovoPacienteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v =  inflater.inflate(R.layout.fragment_tela_novo_paciente, container, false);

        editTextNome = (EditText)v.findViewById(R.id.editTextNome);
        editTextCPF = (EditText)v.findViewById(R.id.editTextCPF);
        editTextSexo = (EditText)v.findViewById(R.id.editTextSexo);
        editTextNascimento = (EditText)v.findViewById(R.id.editTextData);
        editTextDiagnostico = (EditText)v.findViewById(R.id.editTextDiagnostico);
        editTextResponsavel = (EditText)v.findViewById(R.id.editTextResponsavel);
        editTextContato = (EditText)v.findViewById(R.id.editTextContato);
        buttonCancelar = (Button)v.findViewById(R.id.buttonCancelar);
        buttonSalvar = (Button)v.findViewById(R.id.buttonSalvar);

        buttonCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, TelaCandidato.PlaceholderFragment.newInstance())
                        .commit();
            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Paciente p = new Paciente();

                p.setNome(editTextNome.getText().toString());
                p.setCpf(editTextCPF.getText().toString());
                p.setDiagnostico(editTextDiagnostico.getText().toString());
                p.setSexo(editTextSexo.getText().toString());
                p.setNascimento(editTextNascimento.getText().toString());
                p.setNomeResponsavel(editTextResponsavel.getText().toString());
                p.setTelefoneResponsavel(editTextContato.getText().toString());

                Calendar c = Calendar.getInstance();

                Date data = c.getTime();

                p.setData(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(data));

                int dia = Integer.parseInt(p.getNascimento().split("/")[0]);
                int mes = Integer.parseInt(p.getNascimento().split("/")[1]) - 1;
                int ano = Integer.parseInt(p.getNascimento().split("/")[2]);

                c.set(Calendar.DAY_OF_MONTH, dia);
                c.set(Calendar.MONTH, mes);
                c.set(Calendar.YEAR, ano);

                data = c.getTime();

                p.setNascimento(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(data));

                RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
                {
                    @Override
                    public void onRequisitionEnd(String json, int status, Exception e)
                    {
                        if (json != null)
                        {
                            ShowMessage.showToast(getActivity(), "Cadastrado com sucesso");
                        }
                    }
                }, SystemURL.URL + "pacientes", "post", p, getActivity());
            }
        });

        return v;
    }
}
