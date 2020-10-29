package br.com.pan.crud_eventos;

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

import br.com.pan.crud_eventos.R;
import br.com.pan.crud_eventos.database.EventoDAO;
import br.com.pan.crud_eventos.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_EVENTO = 1;
    private final int REQUEST_CODE_EDITAR_EVENTO = 2;
    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EVENTO_DELETE = 12;

    private ListView listviewEventos;
    private ArrayAdapter<Evento> adapterEventos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewEventos = findViewById(R.id.listview_eventos);

        definirOnClickListenerListView();

    }


    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO produtoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1, produtoDAO.listar());
        listviewEventos.setAdapter(adapterEventos);
    }

    public void onClickNovoEvento(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);

    }

    private void definirOnClickListenerListView(){
        listviewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra( "eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

}