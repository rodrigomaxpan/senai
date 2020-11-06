package br.com.pan.crud_eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.pan.crud_eventos.database.LocalDAO;
import br.com.pan.crud_eventos.modelo.Local;

public class ListarLocaisActivity extends AppCompatActivity {


    private ListView listviewLocais;
    private ArrayAdapter<Local> adapterLocais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_locais);
        listviewLocais = findViewById(R.id.listview_locais);

        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<Local>(ListarLocaisActivity.this,
                android.R.layout.simple_list_item_1, localDAO.listar());
        listviewLocais.setAdapter(adapterLocais);
    }

    public void onClickNovoLocal(View v){
        Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);
        startActivity(intent);
    }

    public void onClickListarEventos(View v){
        Intent intent = new Intent(ListarLocaisActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void definirOnClickListenerListView(){
        listviewLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClicado = adapterLocais.getItem(position);
                Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);
                intent.putExtra( "localEdicao", localClicado);
                startActivity(intent);
            }
        });
    }
}