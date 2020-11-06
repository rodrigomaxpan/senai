package br.com.pan.crud_eventos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pan.crud_eventos.R;
import br.com.pan.crud_eventos.database.EventoDAO;
import br.com.pan.crud_eventos.database.LocalDAO;
import br.com.pan.crud_eventos.database.entity.EventoEntity;
import br.com.pan.crud_eventos.database.entity.LocalEntity;
import br.com.pan.crud_eventos.modelo.Evento;
import br.com.pan.crud_eventos.modelo.Local;

public class MainActivity extends AppCompatActivity {


    private ListView listviewEventos;
    private EditText editText_find;
    private ArrayAdapter<Evento> adapterEventos;
    private final String sortASC = " ASC";
    private final String sortDESC = " DESC";
    private String filtroFindApplied;
    private String sortApplied = sortASC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewEventos = findViewById(R.id.listview_eventos);
        editText_find = findViewById(R.id.editText_find);

        editText_find.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                filtroFindApplied = editText_find.getText().toString();
                refreshListViewEventos();
            }
        });

        definirOnClickListenerListView();

    }


    public void onClickNovoEvento(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        filtroFindApplied = "";
        sortApplied = sortASC;
        refreshListViewEventos();
    }

    public void onClickListarEventosASC(View v){
        sortApplied = sortASC;
        refreshListViewEventos();
    }

    public void onClickListarEventosDESC(View v){
        sortApplied = sortDESC;
        refreshListViewEventos();
    }

    public void refreshListViewEventos(){
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listar(filtroFindApplied, EventoEntity.COLUMN_NAME_NOME,sortApplied));
        listviewEventos.setAdapter(adapterEventos);
    }


    public void onClickListarLocais(View v){
        Intent intent = new Intent(MainActivity.this, ListarLocaisActivity.class);
        startActivity(intent);
        finish();
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