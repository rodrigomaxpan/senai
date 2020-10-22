package br.com.pan.crud_eventos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.pan.crud_eventos.R;
import br.com.pan.crud_eventos.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EVENTO_DELETE = 12;
    private boolean edicao = false;
    private String auxId;

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
            edicao = true;
            auxId = evento.getId();
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

        if (edicao){
            Evento evento = new Evento(auxId, nome, local, data);
            intent.putExtra( "eventoEditado", evento);
            setResult(RESULT_CODE_EVENTO_EDITADO,intent);
        }else{
            Evento evento = new Evento(nome,local, data);
            intent.putExtra( "novoEvento", evento);
            setResult(RESULT_CODE_NOVO_EVENTO,intent);
        }

        finish();
    }

    public void onClickExcluir(View v){


        AlertDialog.Builder builder = new AlertDialog.Builder(CadastroEventoActivity.this);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Evento evento = new Evento(auxId, "null", "null", "null");
                Intent intent = new Intent();
                intent.putExtra( "eventoDeletado", evento);
                setResult(RESULT_CODE_EVENTO_DELETE,intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });

        builder.setMessage("Deseja realmente excluir esse evento?");
        builder.setTitle("Confirmar Exclusão:");

        AlertDialog d = builder.create();
        d.show();



    }
}