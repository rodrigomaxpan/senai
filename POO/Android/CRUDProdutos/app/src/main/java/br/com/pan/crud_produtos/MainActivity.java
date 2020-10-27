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
import br.com.pan.crud_produtos.database.ProdutoDAO;
import br.com.pan.crud_produtos.modelo.Produto;

public class MainActivity extends AppCompatActivity {


    private ListView listviewProdutos;
    private ArrayAdapter<Produto> adapterProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewProdutos = findViewById(R.id.listview_produtos);
        definirOnClickListenerListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this,
                android.R.layout.simple_list_item_1, produtoDAO.listar());
        listviewProdutos.setAdapter(adapterProdutos);
    }

    public void onClickNovoProduto(View v){
        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivity(intent);

    }

    private void definirOnClickListenerListView(){
        listviewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoClicado = adapterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intent.putExtra( "produtoEdicao", produtoClicado);
                startActivity(intent);
            }
        });
    }

}