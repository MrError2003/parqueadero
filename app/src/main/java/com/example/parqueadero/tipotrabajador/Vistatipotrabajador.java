package com.example.parqueadero.tipotrabajador;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiTipotrabajador;
import com.example.parqueadero.ApiTipotrabajador;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistatipotrabajador extends AppCompatActivity {

    private TextView resultTextView;
    private ApiTipotrabajador apiTipoTrabajador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistatipotrabajador);

        resultTextView = findViewById(R.id.resultTextViewTipoTrabajador);
        apiTipoTrabajador = new ApiTipotrabajador(this);

        // Llamar a la API y mostrar los resultados
        apiTipoTrabajador.obtenerTiposTrabajador(new ApiTipotrabajador.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject tipoTrabajador = result.getJSONObject(i);
                        String id = tipoTrabajador.getString("id_tipo_trabajador");
                        String nombreTipoTrabajador = tipoTrabajador.getString("n_tipo_trabajador");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre del Tipo de Trabajador: ").append(nombreTipoTrabajador).append("\n\n");
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
