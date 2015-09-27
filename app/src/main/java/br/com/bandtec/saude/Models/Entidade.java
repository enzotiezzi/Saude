package br.com.bandtec.saude.Models;

import java.util.Date;

/**
 * Created by enzo on 26/09/2015.
 */
public class Entidade
{
    private int Id;
    private int IdPaciente;
    private String Data;
    private int IdCuidador;

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public String getData()
    {
        return Data;
    }

    public void setData(String data)
    {
        Data = data;
    }

    public int getIdPaciente()
    {
        return IdPaciente;
    }

    public void setIdPaciente(int idPaciente)
    {
        IdPaciente = idPaciente;
    }

    public int getIdCuidador()
    {
        return IdCuidador;
    }

    public void setIdCuidador(int idCuidador)
    {
        IdCuidador = idCuidador;
    }
}
