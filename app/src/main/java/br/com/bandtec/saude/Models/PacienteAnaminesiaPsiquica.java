package br.com.bandtec.saude.Models;

/**
 * Created by enzo on 27/09/2015.
 */
public class PacienteAnaminesiaPsiquica extends Entidade
{
    private int IdAnaminesiaPsiquica;
    private String Nome;
    private boolean Valor;

    public int getIdAnaminesiaPsiquica()
    {
        return IdAnaminesiaPsiquica;
    }

    public void setIdAnaminesiaPsiquica(int idAnaminesiaPsiquica)
    {
        IdAnaminesiaPsiquica = idAnaminesiaPsiquica;
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
