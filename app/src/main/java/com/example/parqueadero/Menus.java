package com.example.parqueadero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.parqueadero.ciudad.Vistaciudad;
import com.example.parqueadero.departamento.Vistadepartamento;
import com.example.parqueadero.plan.Vistaplan;
import com.example.parqueadero.reserva.Vistareserva;
import com.example.parqueadero.sede.Vistasede;
import com.example.parqueadero.servicio.Vistaservicio;
import com.example.parqueadero.tipotrabajador.Vistatipotrabajador;
import com.example.parqueadero.tipovehiculo.Vistatipovehiculo;
import com.example.parqueadero.trabajador.Vistatrabajador;
import com.example.parqueadero.usuario.Vistausuario;


public class Menus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        setTitle("PKM");
        getSupportActionBar().setSubtitle("Menus");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opcionesmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;

        if (item.getItemId() == R.id.MCiudad) {
            intent = new Intent(this, Vistaciudad.class);
        } else if (item.getItemId() == R.id.MDepartamento) {
            intent = new Intent(this, Vistadepartamento.class);
        } else if (item.getItemId() == R.id.MPlan) {
            intent = new Intent(this, Vistaplan.class);
        } else if (item.getItemId() == R.id.MReserva) {
            intent = new Intent(this, Vistareserva.class);
        } else if (item.getItemId() == R.id.MSede) {
            intent = new Intent(this, Vistasede.class);
        } else if (item.getItemId() == R.id.MServicio) {
            intent = new Intent(this, Vistaservicio.class);
        } else if (item.getItemId() == R.id.MTipoTrabajador) {
            intent = new Intent(this, Vistatipotrabajador.class);
        } else if (item.getItemId() == R.id.MTipoVehiculo) {
            intent = new Intent(this, Vistatipovehiculo.class);
        } else if (item.getItemId() == R.id.MTrabajador) {
            intent = new Intent(this, Vistatrabajador.class);
        } else if (item.getItemId() == R.id.MUsuario) {
            intent = new Intent(this, Vistausuario.class);
        } else if (item.getItemId() == R.id.MVehiculo) {
            intent = new Intent(this, Vistausuario.class);
        }

        if (intent != null) {
            startActivity(intent);
        }

        return true;
    }
}