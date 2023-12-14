package com.example.parqueadero.plan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiPlan;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistaplan extends AppCompatActivity {

    private TextView resultTextView;
    private ApiPlan apiPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistaplan);

        resultTextView = findViewById(R.id.resultTextView);
        apiPlan = new ApiPlan(this);

        // Llamar a la API y mostrar los resultados
        apiPlan.obtenerPlan(new ApiPlan.ApiCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                // Esto no debería ser necesario en este contexto
            }

            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject plan = result.getJSONObject(i);
                        String id = plan.getString("id_plan");
                        String nombrePlan = plan.getString("nombre_plan");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre del Plan: ").append(nombrePlan).append("\n\n");
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
