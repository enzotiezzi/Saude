package br.com.bandtec.saude.Models;

/**
 * Created by enzo on 26/09/2015.
 */
public class PacienteRemedio extends Entidade
{
    private int IdRemedio;

    private String Nome;

    public int getIdRemedio()
    {
        return IdRemedio;
    }

    public void setIdRemedio(int idRemedio)
    {
        IdRemedio = idRemedio;
    }

    public String getNome()
    {
        return Nome;
    }

    public void setNome(String nome)
    {
        Nome = nome;
    }
}
