package com.example.tarearecyclerviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tarearecyclerviews.adapters.EvaluadorAdapter;
import com.example.tarearecyclerviews.models.Evaluador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtResultado;
    private RecyclerView mRecyclerView;

    private EvaluadorAdapter mAdapter;
    private ArrayList<Evaluador> mEvaluadorList = new ArrayList<>();

    private static final String URL1 = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=2";
    private static final String URL = "https://evaladmin.uteq.edu.ec/ws/listadoevaluadores.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        txtResultado = (TextView) findViewById(R.id.txtViewPrueba);


        mRecyclerView = (RecyclerView) findViewById(R.id.rvEvaluadores);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        stringRequest();
        stringRequest2();
        ObtenerEvaluadores();
        //Prueballenar();
    }

    private void  ObtenerEvaluadores(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        txtResultado.append("Objeto Obtenido");
                        try {
                            JSONArray arrayResponse = response.getJSONArray("listaaevaluador");
                            int tamanio = arrayResponse.length();
                            for(int i = 0; i < tamanio; i++){
                                JSONObject jsonObject = new JSONObject(arrayResponse.get(i).toString());

                                String nombre = jsonObject.getString("nombres");
                                String ci = jsonObject.getString("idevaluador");
                                String area = jsonObject.getString("area");
                                String img1 = jsonObject.getString("imgJPG");
                                String img2 = jsonObject.getString("imgjpg");


                                mEvaluadorList.add(new Evaluador(ci, nombre, area, img1, img2));
                            }
                            mAdapter = new EvaluadorAdapter(mEvaluadorList, R.layout.evaluadores_view, MainActivity.this);
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
/*
    private void jsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int issue_id;
                        String titulo;
                        int volumen;
                        int numero;
                        int anio;
                        String cover;

                        int tamanio = response.length();
                        for(int i=0; i<tamanio; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                issue_id = Integer.parseInt(jsonObject.getString("issue_id"));
                                titulo = jsonObject.getString("title");
                                volumen = Integer.parseInt(jsonObject.getString("volume"));
                                numero = Integer.parseInt(jsonObject.getString("number"));
                                anio = Integer.parseInt(jsonObject.getString("year"));
                                cover = jsonObject.getString("cover");
                                mRevistaList.add(new revista(1, titulo, volumen, numero, anio, cover));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter = new RevistaAdapter(mRevistaList, R.layout.revistas_view, MainActivity.this);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }*/


    private void Prueballenar(){
        mEvaluadorList.clear();
        for(int i = 0; i < 10; i++){
            String nombre = "Sebas Carvajal";
            String ci = "0942463746";
            String area = "FCI";
            String img1 = "https://s22.postimg.cc/572fvlmg1/vlad-baranov-767980-unsplash.jpg";
            String img2 = "https://s22.postimg.cc/572fvlmg1/vlad-baranov-767980-unsplash.jpg";

            mEvaluadorList.add(new Evaluador(ci, nombre, area, img1, img2));
        }
        mAdapter = new EvaluadorAdapter(mEvaluadorList, R.layout.evaluadores_view, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

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