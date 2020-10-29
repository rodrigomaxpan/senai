package br.com.pan.crud_eventos.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.pan.crud_eventos.database.entity.EventoEntity;
import br.com.pan.crud_eventos.modelo.Evento;


public class EventoDAO {

    private DBGateway dbGateway;
    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + EventoEntity.TABLE_NAME;


    public EventoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvarEvento(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_LOCAL,evento.getLocal());
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
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String local = cursor.getString((cursor.getColumnIndex(EventoEntity.COLUMN_NAME_LOCAL)));
            String dataHora = cursor.getString((cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA)));
            eventos.add(new Evento(id, nome, local, dataHora));
        }
        cursor.close();
        return eventos;
    }

    public boolean deletarProduto(int id){
        return dbGateway.getDb().delete(EventoEntity.TABLE_NAME, br.com.pan.crud_eventos.database.entity.EventoEntity._ID + " = ?",
                new String[] { String.valueOf(id)}) > 0;
    }
}
