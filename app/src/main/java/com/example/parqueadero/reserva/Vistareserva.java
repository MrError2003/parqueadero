package com.example.parqueadero.reserva;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiReserva;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistareserva extends AppCompatActivity {

    private TextView resultTextView;
    private ApiReserva apiReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistareserva);

        resultTextView = findViewById(R.id.resultTextViewReserva);
        apiReserva = new ApiReserva(this);

        // Llamar a la API y mostrar los resultados
        apiReserva.obtenerReservas(new ApiReserva.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject reserva = result.getJSONObject(i);
                        String id = reserva.getString("id_reserva");
                        String idSede = reserva.getString("id_sede");
                        String idUsuario = reserva.getString("id_usuario");
                        String idTrabajador = reserva.getString("id_trabajador");
                        String idServicio = reserva.getString("id_servicio");
                        String horaReserva = reserva.getString("hora_reserva");
                        String fechaReserva = reserva.getString("fecha_reserva");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID Reserva: ").append(id).append("\nID Sede: ").append(idSede)
                                .append("\nID Usuario: ").append(idUsuario).append("\nID Trabajador: ").append(idTrabajador)
                                .append("\nID Servicio: ").append(idServicio).append("\nHora Reserva: ").append(horaReserva)
                                .append("\nFecha Reserva: ").append(fechaReserva).append("\n\n");
                    }

                    // Mostrar el resultado en el TextView
                    resultTextView.setText(stringBuilder.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    resultTextView.setText("Error al analizar JSON");
                }
            }

            @Override
            public void onError(String error) {
                resultTextView.setText("Error: " + error);
            }
        });
    }
}
