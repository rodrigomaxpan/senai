package br.com.pan.crud_eventos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.pan.crud_eventos.R;
import br.com.pan.crud_eventos.database.EventoDAO;
import br.com.pan.crud_eventos.database.entity.EventoEntity;
import br.com.pan.crud_eventos.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        carregarEventoEdicao();

    }

    private void carregarEventoEdicao(){
        Intent intent = getIntent();
        Button deleteButton = findViewById(R.id.btn_excluir);
        deleteButton.setVisibility(View.GONE);
        if (intent != null && intent.getExtras() != null  &&
                intent.getExtras().get( "eventoEdicao") != null){
            Evento evento =  (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextLocal = findViewById(R.id.editText_local);
            EditText editTextDataHora = findViewById(R.id.editText_data);
            editTextNome.setText(evento.getNome());
            editTextLocal.setText(evento.getLocal());
            editTextDataHora.setText(evento.getDataHora());
            id = evento.getId();
            deleteButton.setVisibility(View.VISIBLE);

        }
    }

    public void onClickVoltar (View v){
        finish();
    }

    public void onClickSalvar (View v){
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextLocal = findViewById(R.id.editText_local);
        EditText editTextDataHora = findViewById(R.id.editText_data);

        String nome = editTextNome.getText().toString();
        String local = editTextLocal.getText().toString();
        String data = editTextDataHora.getText().toString();

        Intent intent = new Intent();

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

        boolean deletou =  eventoDao.deletarProduto(id);
        if (deletou){
            finish();
        }else {
            Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar no banco de dados!", Toast.LENGTH_LONG).show();
        }

    }
}