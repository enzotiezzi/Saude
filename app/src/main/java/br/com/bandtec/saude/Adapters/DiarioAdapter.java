package br.com.bandtec.saude.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.bandtec.saude.Models.DiarioBordo;
import br.com.bandtec.saude.R;

/**
 * Created by enzo on 27/09/2015.
 */
public class DiarioAdapter extends BaseAdapter
{
    private Context contenxt;
    private DiarioBordo[] diario;
    private LayoutInflater layoutInflater;

    public DiarioAdapter(Context context, DiarioBordo[] diario)
    {
        this.contenxt = context;
        this.diario = diario;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return diario.length;
    }

    @Override
    public Object getItem(int position)
    {
        return diario[position];
    }

    @Override
    public long getItemId(int position)
    {
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        DiarioHelper diarioHelper = new DiarioHelper();

        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.item_list_diario, null);

            diarioHelper.textViewTipo = (TextView)convertView.findViewById(R.id.textViewTipo);
            diarioHelper.textViewValor = (TextView)convertView.findViewById(R.id.textViewValor);
            diarioHelper.textViewData = (TextView)convertView.findViewById(R.id.textViewData);

            convertView.setTag(diarioHelper);
        }
        else
        {
            diarioHelper = (DiarioHelper)convertView.getTag();
        }

        DiarioBordo d = diario[position];

        diarioHelper.textViewTipo.setText(d.getTipo());
        diarioHelper.textViewValor.setText(d.getValor());
        diarioHelper.textViewData.setText(d.getData());

        return convertView;
    }

    public class DiarioHelper
    {
        public TextView textViewTipo;
        public TextView textViewValor;
        public TextView textViewData;
    }
}
