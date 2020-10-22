package br.com.pan.crud_produtos.database.contract;

import br.com.pan.crud_produtos.database.entity.ProdutoEntity;
import br.com.pan.crud_produtos.modelo.Produto;

public class ProdutoContract {

    private ProdutoContract() {}

    public static final String criarTabela(){
        return "CREATE TABLE" + ProdutoEntity.TABLE_NAME + "(" +
            ProdutoEntity._ID + "id INTEGER PRIMARY KEY,"  +
                ProdutoEntity.COLUMN_NAME_NOME + "TEXT," +
                ProdutoEntity.COLUMN_NAME_VALOR + "REAL)";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + ProdutoEntity.TABLE_NAME;
    }

}
