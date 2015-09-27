package br.com.bandtec.saude.Models;

import java.util.Date;

/**
 * Created by enzo on 26/09/2015.
 */
public class Paciente extends Entidade
{
    private String Nome;
    private String Cpf;
    private String Sexo;
    private String Nascimento;
    private String NomeResponsavel;
    private String TelefoneResponsavel;
    private String Diagnostico;

    public String getNome()
    {
        return Nome;
    }

    public void setNome(String nome)
    {
        Nome = nome;
    }

    public String getCpf()
    {
        return Cpf;
    }

    public void setCpf(String cpf)
    {
        Cpf = cpf;
    }

    public String getSexo()
    {
        return Sexo;
    }

    public void setSexo(String sexo)
    {
        Sexo = sexo;
    }

    public String getNascimento()
    {
        return Nascimento;
    }

    public void setNascimento(String nascimento)
    {
        Nascimento = nascimento;
    }

    public String getNomeResponsavel()
    {
        return NomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel)
    {
        NomeResponsavel = nomeResponsavel;
    }

    public String getTelefoneResponsavel()
    {
        return TelefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel)
    {
        TelefoneResponsavel = telefoneResponsavel;
    }

    public String getDiagnostico()
    {
        return Diagnostico;
    }

    public void setDiagnostico(String diagnostico)
    {
        Diagnostico = diagnostico;
    }
}
