package com.example.parqueadero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.parqueadero.ciudad.Vistaciudad;
import com.example.parqueadero.departamento.Vistadepartamento;


public class Menus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opcionesmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.MCiudad) {
            Intent irciudad = new Intent(this, Vistaciudad.class);
            startActivity(irciudad);
        } else {

            if (item.getItemId() == R.id.MDepartamento) {
                Intent irdepartamento = new Intent(this, Vistadepartamento.class);
                startActivity(irdepartamento);

        } else {}



        return super.onOptionsItemSelected(item);
    }
}
