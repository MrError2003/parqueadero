package com.example.parqueadero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    TextView texto1, texto2;

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        texto1 = findViewById(R.id.Tid);
        texto2 = findViewById(R.id.Tcontra);

        boton = findViewById(R.id.Bvalidar);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texto1.getText().toString().equals("pepito@gmail.com") && texto2.getText().toString().equals("12345")){
                    Intent smenu = new Intent(Login.this, MenuDos.class);
                    startActivity(smenu);
                } else {
                    Toast.makeText(Login.this, "Usuario invalido", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}