package br.com.bandtec.saude.Views;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import br.com.bandtec.saude.Adapters.PacienteAdapter;
import br.com.bandtec.saude.Fragments.TelaAgendaFragment;
import br.com.bandtec.saude.Fragments.TelaNovoPacienteFragment;
import br.com.bandtec.saude.Models.Paciente;
import br.com.bandtec.saude.Requisition.RequisitionTask;
import br.com.bandtec.saude.Fragments.TelaDiarioFragment;
import br.com.bandtec.saude.Util.NavigationDrawerFragment;
import br.com.bandtec.saude.R;
import br.com.bandtec.saude.Util.Session;
import br.com.bandtec.saude.Utils.SystemURL;

public class TelaCandidato extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_candidato);

        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar_background));

        Window w = getWindow();
        w.setStatusBarColor(getResources().getColor(R.color.corPrimaria));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance())
                .commit();
    }

    public void restoreActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (!mNavigationDrawerFragment.isDrawerOpen())
        {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.tela_candidato, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private ListView listViewPacientes;
        private Button buttonRegistrarPaciente;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance()
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            return fragment;
        }

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_tela_cuidador, container, false);


            listViewPacientes = (ListView) rootView.findViewById(R.id.listViewPacientes);

            buttonRegistrarPaciente = (Button)rootView.findViewById(R.id.buttonRegistrarPAciente);

            RequisitionTask.enviarRequisicao(new RequisitionTask.OnRequisitionEnd()
            {
                @Override
                public void onRequisitionEnd(String json, int status, Exception e)
                {
                    Paciente[] pacientes = new Gson().fromJson(json, Paciente[].class);

                    listViewPacientes.setAdapter(new PacienteAdapter(pacientes, getActivity()));
                }
            }, SystemURL.URL + "pacientes", "get", null, getActivity());

            buttonRegistrarPaciente.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, TelaNovoPacienteFragment.newInstance())
                            .commit();
                }
            });

            listViewPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Paciente p = (Paciente)listViewPacientes.getAdapter().getItem(position);

                    Session.ID = p.getId();

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, TelaAgendaFragment.newInstance())
                            .commit();
                }
            });

            return rootView;
        }
    }

}
