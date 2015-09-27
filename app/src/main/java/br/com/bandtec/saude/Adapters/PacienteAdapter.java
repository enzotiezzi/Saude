package br.com.bandtec.saude.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.bandtec.saude.Models.Paciente;
import br.com.bandtec.saude.R;

/**
 * Created by enzo on 27/09/2015.
 */
public class PacienteAdapter extends BaseAdapter
{
    private Paciente[] pacientes;
    private Context context;
    private LayoutInflater layoutInflater;

    public PacienteAdapter(Paciente[] pacientes, Context context)
    {
        this.pacientes = pacientes;
        this.context = context;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return pacientes.length;
    }

    @Override
    public Object getItem(int position)
    {
        return pacientes[position];
    }

    @Override
    public long getItemId(int position)
    {
        return pacientes[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        PacienteHelper pacienteHelper = new PacienteHelper();
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.item_lista_paciente, null);


            pacienteHelper.Nome = (TextView) convertView.findViewById(R.id.textViewNome);
            pacienteHelper.Idade = (TextView) convertView.findViewById(R.id.textViewIdade);
            pacienteHelper.Diagnostico = (TextView) convertView.findViewById(R.id.textViewDiagnostico);

            convertView.setTag(pacienteHelper);
        }
        else
        {
            pacienteHelper = (PacienteHelper) convertView.getTag();
        }

        Paciente p = pacientes[position];

        pacienteHelper.Nome.setText(p.getNome() == null ? "não tem":p.getNome());
        pacienteHelper.Idade.setText("");
        pacienteHelper.Diagnostico.setText(p.getDiagnostico() == null ? "não tem": p.getDiagnostico());

        return convertView;
    }

    public class PacienteHelper
    {
        public TextView Nome;
        public TextView Idade;
        public TextView Diagnostico;
    }
}
