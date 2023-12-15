package com.example.parqueadero.sede;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiSede;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistasede extends AppCompatActivity {

    private TextView resultTextView;
    private ApiSede apiSede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistasede);

        resultTextView = findViewById(R.id.resultTextViewSede);
        apiSede = new ApiSede(this);

        // Llamar a la API y mostrar los resultados
        apiSede.obtenerSedes(new ApiSede.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject sede = result.getJSONObject(i);
                        String id = sede.getString("id_sede");
                        String nombreSede = sede.getString("sede");
                        String idCiudad = sede.getString("id_ciudad");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre de la Sede: ").append(nombreSede).append("\nID de la Ciudad: ").append(idCiudad).append("\n\n");
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
