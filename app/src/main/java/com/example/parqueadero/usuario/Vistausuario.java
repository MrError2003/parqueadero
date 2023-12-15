package com.example.parqueadero.usuario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parqueadero.ApiUsuario;
import com.example.parqueadero.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vistausuario extends AppCompatActivity {

    private TextView resultTextView;
    private ApiUsuario apiUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistausuario);

        resultTextView = findViewById(R.id.resultTextViewUsuario);
        apiUsuario = new ApiUsuario(this);

        // Llamar a la API y mostrar los resultados
        apiUsuario.obtenerUsuarios(new ApiUsuario.ApiCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Iterar sobre los objetos JSON dentro del JSONArray
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject usuario = result.getJSONObject(i);
                        String id = usuario.getString("id_usuario");
                        String idPlan = usuario.getString("id_plan");
                        String tipoDocumento = usuario.getString("tipo_documento");
                        String nDocumento = usuario.getString("n_documento");
                        String nombre = usuario.getString("nombre");
                        String email = usuario.getString("email");
                        String contrasena = usuario.getString("contrasena");
                        String rol = usuario.getString("rol");

                        // Agregar la información al StringBuilder
                        stringBuilder.append("ID: ").append(id).append("\nID del Plan: ").append(idPlan).append("\nTipo de Documento: ")
                                .append(tipoDocumento).append("\nNúmero de Documento: ").append(nDocumento).append("\nNombre: ").append(nombre)
                                .append("\nEmail: ").append(email).append("\nContraseña: ").append(contrasena).append("\nRol: ").append(rol).append("\n\n");
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
