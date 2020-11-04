package br.com.pan.crud_eventos.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.pan.crud_eventos.database.entity.EventoEntity;
import br.com.pan.crud_eventos.database.entity.LocalEntity;
import br.com.pan.crud_eventos.modelo.Evento;
import br.com.pan.crud_eventos.modelo.Local;


public class EventoDAO {

    private DBGateway dbGateway;
    private final String SQL_LISTAR_TODOS = "SELECT " +
            EventoEntity.TABLE_NAME + "." + EventoEntity._ID + ", " +
            EventoEntity.TABLE_NAME + "." + EventoEntity.COLUMN_NAME_ID_LOCAL + ", " +
            EventoEntity.TABLE_NAME + "." + EventoEntity.COLUMN_NAME_NOME + ", " +
            EventoEntity.TABLE_NAME + "." + EventoEntity.COLUMN_NAME_DATA + ", " +
            LocalEntity.TABLE_NAME + "." + LocalEntity.COLUMN_NAME_NOME+ ", " +
            LocalEntity.TABLE_NAME + "." + LocalEntity.COLUMN_NAME_BAIRRO+ ", " +
            LocalEntity.TABLE_NAME + "." + LocalEntity.COLUMN_NAME_CIDADE+ ", " +
            LocalEntity.TABLE_NAME + "." + LocalEntity.COLUMN_NAME_CAPACIDADE+ " FROM " +
            EventoEntity.TABLE_NAME +

            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCAL +
            " = " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID;


    public EventoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvarEvento(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCAL,evento.getLocal().getId());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA,evento.getDataHora());
        if (evento.getId() > 0){
            return dbGateway.getDb().update(
                    EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + " = ?",
                    new String []{ String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDb().insert(EventoEntity.TABLE_NAME, null, contentValues) > 0;

    }

    public List<Evento> listar(){
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDb().rawQuery(SQL_LISTAR_TODOS, null   );
        while (cursor.moveToNext()){
            int id  = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            int idLocal = cursor.getInt((cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAL)));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String dataHora = cursor.getString((cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA)));
            String nomeLocal = cursor.getString((cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME)));
            String bairro = cursor.getString((cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO)));
            String cidade = cursor.getString((cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE)));
            int capacidade = cursor.getInt((cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE)));
            Local local = new Local(idLocal,nomeLocal,bairro,cidade,capacidade);
            eventos.add(new Evento(id, nomeEvento, local, dataHora));
        }
        cursor.close();
        return eventos;
    }

    public boolean deletarProduto(int id){
        return dbGateway.getDb().delete(EventoEntity.TABLE_NAME, br.com.pan.crud_eventos.database.entity.EventoEntity._ID + " = ?",
                new String[] { String.valueOf(id)}) > 0;
    }
}
