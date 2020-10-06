package com.example.senai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickBtnMostrar(View v){
        EditText editText1 = findViewById(R.id.et_texto1);
        EditText editText2 = findViewById(R.id.et_texto2);
        String auxText = editText1.getText().toString() + " - " + editText2.getText().toString();
        Toast.makeText(MainActivity.this, auxText, Toast.LENGTH_SHORT).show();
    }

    public void onClickBtnLimpar(View v){
        EditText editText1 = findViewById(R.id.et_texto1);
        EditText editText2 = findViewById(R.id.et_texto2);
        editText1.setText("");
        editText2.setText("");

    }

}