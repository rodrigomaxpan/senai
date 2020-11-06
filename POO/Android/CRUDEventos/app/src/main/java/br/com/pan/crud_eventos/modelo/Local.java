package br.com.pan.crud_eventos.modelo;

import java.io.Serializable;

public class Local implements Serializable {

    private int id;
    private String nome;
    private String bairro;
    private String cidade;
    private int capacidade;

    public Local(int id, String nome, String cidade, String bairro, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.bairro = bairro;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return nome + " - " + cidade;
    }
}
