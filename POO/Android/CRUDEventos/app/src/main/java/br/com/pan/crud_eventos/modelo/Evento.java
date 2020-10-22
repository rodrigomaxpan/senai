package br.com.pan.crud_eventos.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Evento implements Serializable {


    private String id;
    private String nome;
    private String local;
    private String dataHora;

    public Evento(String nome, String local, String dataHora) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.local = local;
        this.dataHora = dataHora;

    }

    public Evento(String id, String nome, String local, String dataHora) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.dataHora = dataHora;
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }


    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    @Override
    public String toString() {
        return nome + " - " + local + " - " + dataHora;
    }
}