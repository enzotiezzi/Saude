package br.com.bandtec.saude.Models;

/**
 * Created by enzo on 27/09/2015.
 */
public class PacienteAnaminesiaFisica extends Entidade
{
    private int IdAnaminesiaFisica;
    private String Nome;
    private boolean Valor;

    public int getIdAnaminesiaFisica()
    {
        return IdAnaminesiaFisica;
    }

    public void setIdAnaminesiaFisica(int idAnaminesiaFisica)
    {
        IdAnaminesiaFisica = idAnaminesiaFisica;
    }

    public String getNome()
    {
        return Nome;
    }

    public void setNome(String nome)
    {
        Nome = nome;
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
