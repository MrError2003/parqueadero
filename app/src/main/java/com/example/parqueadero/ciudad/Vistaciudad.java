package com.example.parqueadero.ciudad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiCiudad;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistaciudad extends AppCompatActivity {

    private TextView resultTextView;
    private ApiCiudad apiCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistaciudad);

        resultTextView = findViewById(R.id.resultTextViewCiudad);
        apiCiudad = new ApiCiudad(this);

        // Llamar a la API y mostrar los resultados
        apiCiudad.obtenerCiudades(new ApiCiudad.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject ciudad = result.getJSONObject(i);
                        String id = ciudad.getString("id_ciudad");
                        String nombreCiudad = ciudad.getString("ciudad");
                        String idDepartamento = ciudad.getString("id_departamento");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre de la Ciudad: ").append(nombreCiudad).append("\nID del Departamento: ").append(idDepartamento).append("\n\n");
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
