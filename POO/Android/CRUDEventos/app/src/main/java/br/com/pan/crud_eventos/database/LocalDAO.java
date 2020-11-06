package br.com.pan.crud_eventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;


import br.com.pan.crud_eventos.database.entity.LocalEntity;
import br.com.pan.crud_eventos.modelo.Local;

public class LocalDAO {

    private DBGateway dbGateway;
    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + LocalEntity.TABLE_NAME;

    public LocalDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }


    public boolean salvarLocal(Local local  ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_NOME, local.getNome());
        contentValues.put(LocalEntity.COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE, local.getCapacidade());
        if (local.getId() > 0){
            return dbGateway.getDb().update(
                    LocalEntity.TABLE_NAME,
                    contentValues,
                    LocalEntity._ID + " = ?",
                    new String []{ String.valueOf(local.getId())}) > 0;
        }
        return dbGateway.getDb().insert(LocalEntity.TABLE_NAME, null, contentValues) > 0;

    }

    public List<Local> listar(){
        List<Local> locais = new ArrayList<>();
        Cursor cursor = dbGateway.getDb().rawQuery(SQL_LISTAR_TODOS, null   );
        while (cursor.moveToNext()){
            int id  = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String cidade = cursor.getString((cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE)));
            String bairro = cursor.getString((cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO)));
            int capacidade = cursor.getInt((cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE)));
            locais.add(new Local(id, nome, cidade, bairro, capacidade));
        }
        cursor.close();
        return locais;
    }

    public boolean deletarLocal(int id){
        return dbGateway.getDb().delete(LocalEntity.TABLE_NAME, br.com.pan.crud_eventos.database.entity.LocalEntity._ID + " = ?",
                new String[] { String.valueOf(id)}) > 0;
    }



}
