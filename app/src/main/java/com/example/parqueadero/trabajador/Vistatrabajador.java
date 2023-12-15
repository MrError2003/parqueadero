package com.example.parqueadero.trabajador;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiTrabajador;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistatrabajador extends AppCompatActivity {

    private TextView resultTextView;
    private ApiTrabajador apiTrabajador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistatrabajador);

        resultTextView = findViewById(R.id.resultTextViewTrabajador);
        apiTrabajador = new ApiTrabajador(this);

        // Llamar a la API y mostrar los resultados
        apiTrabajador.obtenerTrabajadores(new ApiTrabajador.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject trabajador = result.getJSONObject(i);
                        String id = trabajador.getString("id_trabajador");
                        String nombreTrabajador = trabajador.getString("nombre_trabajador");
                        String idTipoTrabajador = trabajador.getString("id_tipo_trabajador");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre del Trabajador: ").append(nombreTrabajador).append("\nID del Tipo de Trabajador: ").append(idTipoTrabajador).append("\n\n");
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
