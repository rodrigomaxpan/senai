package br.com.pan.crud_produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.pan.crud_produtos.R;
import br.com.pan.crud_produtos.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;
    private final int RESULT_CODE_PRODUTO_DELETE = 12;
    private boolean edicao = false;
    private String auxId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        carregarProdutoEdicao();

    }

    private void carregarProdutoEdicao(){
        Intent intent = getIntent();
        Button deleteButton = findViewById(R.id.btn_excluir);
        deleteButton.setVisibility(View.GONE);
        if (intent != null && intent.getExtras() != null  &&
                intent.getExtras().get( "produtoEdicao") != null){
            Produto produto =  (Produto) intent.getExtras().get("produtoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextValor = findViewById(R.id.editText_valor);
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            edicao = true;
            auxId = produto.getId();
            deleteButton.setVisibility(View.VISIBLE);

        }
    }

    public void onClickVoltar (View v){
        finish();
    }

    public void onClickSalvar (View v){
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextvalor = findViewById(R.id.editText_valor);

        String nome = editTextNome.getText().toString();
        float valor = Float.parseFloat(editTextvalor.getText().toString());


        Intent intent = new Intent();

        if (edicao){
            Produto produto = new Produto(auxId, nome, valor);
            intent.putExtra( "produtoEditado", produto);
            setResult(RESULT_CODE_PRODUTO_EDITADO,intent);
        }else{
            Produto produto = new Produto(nome,valor);
            intent.putExtra( "novoProduto", produto);
            setResult(RESULT_CODE_NOVO_PRODUTO,intent);
        }

        finish();
    }

    public void onClickExcluir(View v){

        Produto produto = new Produto(auxId, "null", 0);
        Intent intent = new Intent();
        intent.putExtra( "produtoDeletado", produto);
        setResult(RESULT_CODE_PRODUTO_DELETE,intent);
        finish();

    }
}