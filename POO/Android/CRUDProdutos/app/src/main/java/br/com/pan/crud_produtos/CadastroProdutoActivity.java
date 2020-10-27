package br.com.pan.crud_produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.pan.crud_produtos.R;
import br.com.pan.crud_produtos.database.ProdutoDAO;
import br.com.pan.crud_produtos.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {


    private int id = 0;

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
            id = produto.getId();
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

        Produto produto = new Produto(id, nome, valor);
        Intent intent = new Intent();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        boolean salvou = produtoDAO.salvarProduto(produto);
        if (salvou){
            finish();
        }else {
                Toast.makeText(CadastroProdutoActivity.this, "Erro ao salvar no banco de dados!", Toast.LENGTH_LONG).show();
        }

        finish();
    }

    public void onClickExcluir(View v){

        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());

        boolean deletou =  produtoDAO.deletarProduto(id);
        if (deletou){
            finish();
        }else {
            Toast.makeText(CadastroProdutoActivity.this, "Erro ao salvar no banco de dados!", Toast.LENGTH_LONG).show();
        }

    }
}