package br.com.pan.crud_produtos.database;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DBGateway {

    private static DBGateway dbGateway;
    private SQLiteDatabase db;

    public static DBGateway getInstance(Context context){
        if (dbGateway == null){
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    private DBGateway(Context context){

        DatabaseDBHelper dbHelper = new DatabaseDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
