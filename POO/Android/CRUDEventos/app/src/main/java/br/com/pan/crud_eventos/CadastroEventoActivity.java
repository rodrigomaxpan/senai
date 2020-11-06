package br.com.pan.crud_eventos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.pan.crud_eventos.R;
import br.com.pan.crud_eventos.database.EventoDAO;
import br.com.pan.crud_eventos.database.LocalDAO;
import br.com.pan.crud_eventos.database.entity.EventoEntity;
import br.com.pan.crud_eventos.modelo.Evento;
import br.com.pan.crud_eventos.modelo.Local;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;

    private Spinner spinnerLocais;
    private ArrayAdapter<Local> adapterLocais;
    private EditText editTextNome;
    private EditText editTextDataHora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        spinnerLocais = findViewById(R.id.spinner_locais);

        editTextNome = findViewById(R.id.editText_nome);
        editTextDataHora = findViewById(R.id.editText_data);
        carregarLocais();
        carregarEventoEdicao();
    }

    private void carregarLocais(){
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<>(CadastroEventoActivity.this,
                android.R.layout.simple_spinner_item, localDAO.listar());
        spinnerLocais.setAdapter(adapterLocais);
    }

    private void carregarEventoEdicao(){
        Intent intent = getIntent();
        Button deleteButton = findViewById(R.id.btn_excluir);
        deleteButton.setVisibility(View.GONE);
        if (intent != null && intent.getExtras() != null  &&
                intent.getExtras().get( "eventoEdicao") != null){
            Evento evento =  (Evento) intent.getExtras().get("eventoEdicao");
            editTextNome.setText(evento.getNome());
            editTextDataHora.setText(evento.getDataHora());
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);
            id = evento.getId();
            deleteButton.setVisibility(View.VISIBLE);

        }
    }

    private int obterPosicaoLocal(Local local){
        for (int posicao = 0 ; posicao < adapterLocais.getCount(); posicao++) {
            if (adapterLocais.getItem(posicao).getId() == local.getId()){
                return posicao;
            }
        }
        return 0;
    }


    public void onClickVoltar (View v){
        finish();
    }

    public void onClickSalvar (View v){
        String nome = editTextNome.getText().toString();
        String data = editTextDataHora.getText().toString();
        Local local = (Local) spinnerLocais.getSelectedItem();
        Evento evento = new Evento(id, nome, local, data);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvarEvento(evento);
        if (salvou){
            finish();
        }else {
            Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar no banco de dados!", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    public void onClickExcluir(View v){

        EventoDAO eventoDao = new EventoDAO(getBaseContext());

        boolean deletou =  eventoDao.deletarEvento(id);
        if (deletou){
            finish();
        }else {
            Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar no banco de dados!", Toast.LENGTH_LONG).show();
        }

    }
}