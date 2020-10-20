package br.com.pan.crud_produtos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pan.crud_produtos.R;
import br.com.pan.crud_produtos.modelo.Produto;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_PRODUTO = 1;
    private final int REQUEST_CODE_EDITAR_PRODUTO = 2;
    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;
    private final int RESULT_CODE_PRODUTO_DELETE = 12;

    private ListView listviewProdutos;
    private ArrayAdapter<Produto> adapterProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewProdutos = findViewById(R.id.listview_produtos);
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this,
                android.R.layout.simple_list_item_1, produtos);
        listviewProdutos.setAdapter(adapterProdutos);

        definirOnClickListenerListView();


    }


    public void onClickNovoProduto(View v){
        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivityForResult(intent,REQUEST_CODE_NOVO_PRODUTO);

    }

    private void definirOnClickListenerListView(){
        listviewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoClicado = adapterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intent.putExtra( "produtoEdicao", produtoClicado);
                startActivityForResult(intent, REQUEST_CODE_EDITAR_PRODUTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NOVO_PRODUTO && resultCode == RESULT_CODE_NOVO_PRODUTO){
            Produto produto = (Produto) data.getExtras().getSerializable( "novoProduto");
            this.adapterProdutos.add(produto);
            Toast.makeText(MainActivity.this, "Produto criado!", Toast.LENGTH_LONG);
        }else if (requestCode == REQUEST_CODE_EDITAR_PRODUTO && resultCode == RESULT_CODE_PRODUTO_EDITADO) {
            Produto produtoEditado = (Produto) data.getExtras().getSerializable( "produtoEditado");
            for (int i = 0; i < adapterProdutos.getCount(); i++){
                Produto produto = adapterProdutos.getItem(i);
                if (produto.getId().equals(produtoEditado.getId())){
                    adapterProdutos.remove(produto);
                    adapterProdutos.insert(produtoEditado,i);
                    break;
                 }
            }
            Toast.makeText(MainActivity.this, "Produto editado!", Toast.LENGTH_LONG);
        }else if (resultCode == RESULT_CODE_PRODUTO_DELETE){
            Produto produtoDeletado = (Produto) data.getExtras().getSerializable( "produtoDeletado");
            for (int i = 0; i < adapterProdutos.getCount(); i++){
                Produto produto = adapterProdutos.getItem(i);
                if (produto.getId().equals(produtoDeletado.getId())){
                    adapterProdutos.remove(produto);
                    break;
                }
            }
            Toast.makeText(MainActivity.this, "Produto excluído!", Toast.LENGTH_LONG);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}