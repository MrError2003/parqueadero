package com.example.parqueadero.departamento;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiDepartamento;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistadepartamento extends AppCompatActivity {

    private TextView resultTextView;
    private ApiDepartamento apiDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistadepartamento);

        resultTextView = findViewById(R.id.resultTextViewDepartamento);
        apiDepartamento = new ApiDepartamento(this);

        // Llamar a la API y mostrar los resultados
        apiDepartamento.obtenerDepartamentos(new ApiDepartamento.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject departamento = result.getJSONObject(i);
                        String id = departamento.getString("id_departamento");
                        String nombreDepartamento = departamento.getString("departamento");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre del Departamento: ").append(nombreDepartamento).append("\n\n");
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
