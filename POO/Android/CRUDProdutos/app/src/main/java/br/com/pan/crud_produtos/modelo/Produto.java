package br.com.pan.crud_produtos.modelo;

import java.io.Serializable;
import java.util.UUID;

public class Produto implements Serializable {


    private int id;
    private String nome;
    private float valor;

    public Produto(String nome, float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(int id, String nome, float valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + valor;
    }
}
