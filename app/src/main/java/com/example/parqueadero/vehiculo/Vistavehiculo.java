package com.example.parqueadero.vehiculo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiVehiculo;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistavehiculo extends AppCompatActivity {

    private TextView resultTextView;
    private ApiVehiculo apiVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistavehiculo);

        resultTextView = findViewById(R.id.resultTextViewVehiculo);
        apiVehiculo = new ApiVehiculo(this);

        // Llamar a la API y mostrar los resultados
        apiVehiculo.obtenerVehiculos(new ApiVehiculo.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject vehiculo = result.getJSONObject(i);
                        String id = vehiculo.getString("id_vehiculo");
                        String idTipoVehiculo = vehiculo.getString("id_tipo_vehiculo");
                        String placa = vehiculo.getString("placa_vehiculo");
                        String idUsuario = vehiculo.getString("id_usuario");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nID del Tipo de Vehículo: ").append(idTipoVehiculo)
                                .append("\nPlaca del Vehículo: ").append(placa).append("\nID del Usuario: ").append(idUsuario).append("\n\n");
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
