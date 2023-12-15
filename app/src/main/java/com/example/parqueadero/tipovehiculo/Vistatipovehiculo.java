package com.example.parqueadero.tipovehiculo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiTipovehiculo;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistatipovehiculo extends AppCompatActivity {

    private TextView resultTextView;
    private ApiTipovehiculo apiTipoVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistatipovehiculo);

        resultTextView = findViewById(R.id.resultTextViewTipoVehiculo);
        apiTipoVehiculo = new ApiTipovehiculo(this);

        // Llamar a la API y mostrar los resultados
        apiTipoVehiculo.obtenerTiposVehiculo(new ApiTipovehiculo.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject tipoVehiculo = result.getJSONObject(i);
                        String id = tipoVehiculo.getString("id_tipo_vehiculo");
                        String nombreTipoVehiculo = tipoVehiculo.getString("nombre_tipo_vehiculo");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nNombre del Tipo de Vehículo: ").append(nombreTipoVehiculo).append("\n\n");
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
