package br.com.bandtec.saude.Models;

/**
 * Created by enzo on 26/09/2015.
 */
public class Agenda extends Entidade
{
    private String NomeDoRemedio;
    private int Periodicidade;


    public String getNomeDoRemedio()
    {
        return NomeDoRemedio;
    }

    public void setNomeDoRemedio(String nomeDoRemedio)
    {
        NomeDoRemedio = nomeDoRemedio;
    }

    public int getPeriodicidade()
    {
        return Periodicidade;
    }

    public void setPeriodicidade(int periodicidade)
    {
        Periodicidade = periodicidade;
    }
}
