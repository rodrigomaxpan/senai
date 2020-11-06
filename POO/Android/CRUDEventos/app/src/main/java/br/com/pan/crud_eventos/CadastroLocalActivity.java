package br.com.pan.crud_eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.com.pan.crud_eventos.database.LocalDAO;
import br.com.pan.crud_eventos.modelo.Local;

public class CadastroLocalActivity extends AppCompatActivity {

    private int id = 0;

    private EditText editTextNome;
    private EditText editTextCidade;
    private EditText editTextBairro;
    private EditText editTextCapacidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        carregarLocalEdicao();
    }

    public void onClickVoltar (View v){
        finish();
    }

    private void carregarLocalEdicao(){
        Intent intent = getIntent();
        Button deleteButton = findViewById(R.id.btn_excluir);

        editTextNome = findViewById(R.id.editText_nome);
        editTextCidade = findViewById(R.id.editText_cidade);
        editTextBairro = findViewById(R.id.editText_bairro);
        editTextCapacidade = findViewById(R.id.editText_capacidade);

        deleteButton.setVisibility(View.GONE);


        if (intent != null && intent.getExtras() != null  &&
                intent.getExtras().get( "localEdicao") != null){
            Local local =  (Local) intent.getExtras().get("localEdicao");

            editTextNome.setText(local.getNome());
            editTextCidade.setText(local.getCidade());
            editTextBairro.setText(local.getBairro());
            editTextCapacidade.setText(String.valueOf(local.getCapacidade()));
            id = local.getId();
            deleteButton.setVisibility(View.VISIBLE);

        }
    }


    public void onClickSalvar (View v){

        String nome = editTextNome.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String bairro = editTextBairro.getText().toString();
        int capacidade = Integer.parseInt(editTextCapacidade.getText().toString());

        Local local = new Local(id, nome,cidade, bairro, capacidade);

        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvarLocal(local);

        if (salvou){
            finish();
        }else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar no banco de dados!", Toast.LENGTH_LONG).show();
        }

        finish();
    }


    public void onClickExcluir(View v){

        LocalDAO localDao = new LocalDAO(getBaseContext());

        boolean deletou =  localDao.deletarLocal(id);
        if (deletou){
            finish();
        }else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar no banco de dados!", Toast.LENGTH_LONG).show();
        }

    }
}