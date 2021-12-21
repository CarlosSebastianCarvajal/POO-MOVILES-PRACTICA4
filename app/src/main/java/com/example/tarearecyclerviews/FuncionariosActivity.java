package com.example.tarearecyclerviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tarearecyclerviews.adapters.EvaluadorAdapter;
import com.example.tarearecyclerviews.adapters.FuncionarioAdapter;
import com.example.tarearecyclerviews.models.Evaluador;
import com.example.tarearecyclerviews.models.Funcionario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FuncionariosActivity extends AppCompatActivity {

    TextView txtResultado;
    private RecyclerView mRecyclerView;

    private FuncionarioAdapter mAdapter;
    private ArrayList<Funcionario> mFuncionarioList = new ArrayList<>();

    private static final String URL1 = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=2";
    private static final String URL = "https://evaladmin.uteq.edu.ec/ws/listadoaevaluar.php?e=";
    RequestQueue requestQueue;

    String e;

    Bundle b = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionarios);

        b = this.getIntent().getExtras();

        e = b.getString("id");


        requestQueue = Volley.newRequestQueue(this);

        txtResultado = (TextView) findViewById(R.id.txtViewPruebaF);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvFuncionarios);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        stringRequest();
        //stringRequest2();
        ObtenerFuncionarios();
    }

    private void  ObtenerFuncionarios(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL + e,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        txtResultado.append("Objeto Obtenido");
                        try {
                            JSONArray arrayResponse = response.getJSONArray("listaaevaluar");
                            int tamanio = arrayResponse.length();
                            for(int i = 0; i < tamanio; i++){
                                JSONObject jsonObject = new JSONObject(arrayResponse.get(i).toString());

                                String id = jsonObject.getString("id");
                                String idevaluado = jsonObject.getString("idevaluado");
                                String nombres = jsonObject.getString("Nombres");
                                String genero = jsonObject.getString("genero");
                                String situacion = jsonObject.getString("situacion");
                                String cargo = jsonObject.getString("cargo");
                                String fechainicio = jsonObject.getString("fechainicio");
                                String fechafin = jsonObject.getString("fechafin");
                                String imgJPG = jsonObject.getString("imgJPG");
                                String imgjpg = jsonObject.getString("imgjpg");

                                mFuncionarioList.add(new Funcionario(id, idevaluado, nombres, genero, situacion, cargo, fechainicio, fechafin, imgJPG, imgjpg));
                            }
                            mAdapter = new FuncionarioAdapter(mFuncionarioList, R.layout.funcionarios_view, FuncionariosActivity.this);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void stringRequest(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtResultado.append("DO..1");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);
    }
    private void stringRequest2(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtResultado.append("DO..2");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);
    }
}