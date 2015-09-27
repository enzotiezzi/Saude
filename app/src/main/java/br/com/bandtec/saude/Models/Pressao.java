package br.com.bandtec.saude.Models;

/**
 * Created by enzo on 26/09/2015.
 */
public class Pressao extends Entidade
{
    private int Sistolica;
    private int Diastólica;

    public int getSistolica()
    {
        return Sistolica;
    }

    public void setSistolica(int sistolica)
    {
        Sistolica = sistolica;
    }

    public int getDiastolica()
    {
        return Diastólica;
    }

    public void setDiastolica(int diastolica)
    {
        Diastólica = diastolica;
    }
}
