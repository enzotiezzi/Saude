package br.com.bandtec.saude.Models;

/**
 * Created by enzo on 26/09/2015.
 */
public class AnaminesiaFisica extends Anaminesia
{
    private int IdAnaminesiaFisica;
    private boolean Valor;


    public int getIdAnaminesiaFisica()
    {
        return IdAnaminesiaFisica;
    }

    public void setIdAnaminesiaFisica(int idAnaminesiaFisica)
    {
        IdAnaminesiaFisica = idAnaminesiaFisica;
    }


    public boolean isValor()
    {
        return Valor;
    }

    public void setValor(boolean valor)
    {
        Valor = valor;
    }
}
