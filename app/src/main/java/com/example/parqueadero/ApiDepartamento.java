package com.example.parqueadero;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class ApiDepartamento {

    private static final String baseUrl = "https://tu_servidor.com/tu_ruta_api/departamento.php/";

    public interface ApiCallback {
        void onSuccess(JSONArray result);
        void onError(String error);
    }

    private final RequestQueue requestQueue;

    public ApiDepartamento(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void obtenerDepartamentos(final ApiCallback callback){
        String url = baseUrl + "departamento";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        callback.onError("Error en la solicitud HTTP: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}
