package br.com.bandtec.saude.Models;

import java.util.List;

/**
 * Created by enzo on 26/09/2015.
 */
public class Cuidador
{
    private int Id;
    private String Nome;
    private String Cpf;
    private String Senha;
    private List<Paciente> Pacientes;


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

    public String getSenha()
    {
        return Senha;
    }

    public void setSenha(String senha)
    {
        Senha = senha;
    }

    public List<Paciente> getPacientes()
    {
        return Pacientes;
    }

    public void setPacientes(List<Paciente> pacientes)
    {
        Pacientes = pacientes;
    }

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }
}
