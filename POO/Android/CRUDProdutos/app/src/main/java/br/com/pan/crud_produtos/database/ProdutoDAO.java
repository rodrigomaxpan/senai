package br.com.pan.crud_produtos.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.Contacts;

import java.util.ArrayList;
import java.util.List;

import br.com.pan.crud_produtos.database.entity.ProdutoEntity;
import br.com.pan.crud_produtos.modelo.Produto;

public class ProdutoDAO {

    private DBGateway dbGateway;
    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + ProdutoEntity.TABLE_NAME;


    public ProdutoDAO (Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvarProduto(Produto produto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProdutoEntity.COLUMN_NAME_NOME, produto.getNome());
        contentValues.put(ProdutoEntity.COLUMN_NAME_VALOR,produto.getValor());
        if (produto.getId() > 0){
            return dbGateway.getDb().update(
                    ProdutoEntity.TABLE_NAME,
                    contentValues,
                    ProdutoEntity._ID + " = ?",
                    new String []{ String.valueOf(produto.getId())}) > 0;
        }
        return dbGateway.getDb().insert(ProdutoEntity.TABLE_NAME, null, contentValues) > 0;

    }

    public List<Produto> listar(){
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = dbGateway.getDb().rawQuery(SQL_LISTAR_TODOS, null   );
        while (cursor.moveToNext()){
            int id  = cursor.getInt(cursor.getColumnIndex(ProdutoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(ProdutoEntity.COLUMN_NAME_NOME));
            Float valor = cursor.getFloat((cursor.getColumnIndex(ProdutoEntity.COLUMN_NAME_VALOR)));
            produtos.add(new Produto(id, nome, valor));
        }
        cursor.close();
        return produtos;
    }

    public boolean deletarProduto(int id){
        return dbGateway.getDb().delete(ProdutoEntity.TABLE_NAME, ProdutoEntity._ID + " = ?",
                new String[] { String.valueOf(id)}) > 0;
    }
}
