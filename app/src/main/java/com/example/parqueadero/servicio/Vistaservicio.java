package com.example.parqueadero.servicio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiServicio;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistaservicio extends AppCompatActivity {

    private TextView resultTextView;
    private ApiServicio apiServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistaservicio);

        resultTextView = findViewById(R.id.resultTextViewServicio);
        apiServicio = new ApiServicio(this);

        // Llamar a la API y mostrar los resultados
        apiServicio.obtenerServicios(new ApiServicio.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject servicio = result.getJSONObject(i);
                        String id = servicio.getString("id_servicio");
                        String nombreServicio = servicio.getString("n_servicio");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre del Servicio: ").append(nombreServicio).append("\n\n");
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
