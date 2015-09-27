package br.com.bandtec.saude.Models;

/**
 * Created by enzo on 27/09/2015.
 */
public class DiarioBordo
{
    private String Tipo;
    private String Data;
    private String Valor;

    public String getTipo()
    {
        return Tipo;
    }

    public void setTipo(String tipo)
    {
        Tipo = tipo;
    }

    public String getData()
    {
        return Data;
    }

    public void setData(String data)
    {
        Data = data;
    }

    public String getValor()
    {
        return Valor;
    }

    public void setValor(String valor)
    {
        Valor = valor;
    }
}
