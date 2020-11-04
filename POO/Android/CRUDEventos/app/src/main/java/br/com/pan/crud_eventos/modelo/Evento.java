package br.com.pan.crud_eventos.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Evento implements Serializable {


    private int id;
    private String nome;
    private Local local;
    private String dataHora;

    public Evento(String nome, Local local, String dataHora) {
        this.nome = nome;
        this.local = local;
        this.dataHora = dataHora;

    }

    public Evento(int id, String nome, Local local, String dataHora) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.dataHora = dataHora;
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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return nome + " - " + local + " - " + dataHora;
    }
}
