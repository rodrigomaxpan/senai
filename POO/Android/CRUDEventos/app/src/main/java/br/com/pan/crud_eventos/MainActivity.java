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
        ArrayList<Evento> eventos = new ArrayList<Evento>();

        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1, eventos);
        listviewEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();


    }


    public void onClickNovoEvento(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NOVO_EVENTO);

    }

    private void definirOnClickListenerListView(){
        listviewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra( "eventoEdicao", eventoClicado);
                startActivityForResult(intent, REQUEST_CODE_EDITAR_EVENTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NOVO_EVENTO && resultCode == RESULT_CODE_NOVO_EVENTO){
            Evento evento = (Evento) data.getExtras().getSerializable( "novoEvento");
            this.adapterEventos.add(evento);
            Toast.makeText(MainActivity.this, "Produto criado!", Toast.LENGTH_LONG);
        }else if (requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EVENTO_EDITADO) {
            Evento eventoEditado = (Evento) data.getExtras().getSerializable( "eventoEditado");
            for (int i = 0; i < adapterEventos.getCount(); i++){
                Evento evento = adapterEventos.getItem(i);
                if (evento.getId().equals(eventoEditado.getId())){
                    adapterEventos.remove(evento);
                    adapterEventos.insert(eventoEditado,i);
                    break;
                 }
            }
            Toast.makeText(MainActivity.this, "O evento foi editado!", Toast.LENGTH_LONG);
        }else if (resultCode == RESULT_CODE_EVENTO_DELETE){
            Evento eventoDeletado = (Evento) data.getExtras().getSerializable( "eventoDeletado");
            for (int i = 0; i < adapterEventos.getCount(); i++){
                Evento evento = adapterEventos.getItem(i);
                if (evento.getId().equals(eventoDeletado.getId())){
                    adapterEventos.remove(evento);
                    break;
                }
            }
            Toast.makeText(MainActivity.this, "Evento excluído!", Toast.LENGTH_LONG);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}