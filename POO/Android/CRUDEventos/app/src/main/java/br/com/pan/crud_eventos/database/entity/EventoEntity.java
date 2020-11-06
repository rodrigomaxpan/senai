package br.com.pan.crud_eventos.database.entity;


import android.provider.BaseColumns;

public final class EventoEntity implements BaseColumns {

    private EventoEntity() {}

    public static final String TABLE_NAME = "evento";
    public static final String COLUMN_NAME_NOME = "nomeEvento";
    public static final String COLUMN_NAME_DATA = "data";
    public static final String COLUMN_NAME_ID_LOCAL = "idLocal";

}
