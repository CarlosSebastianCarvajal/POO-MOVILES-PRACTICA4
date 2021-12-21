package com.example.tarearecyclerviews.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tarearecyclerviews.FuncionariosActivity;
import com.example.tarearecyclerviews.R;
import com.example.tarearecyclerviews.models.Evaluador;

import java.util.ArrayList;

public class EvaluadorAdapter extends RecyclerView.Adapter<EvaluadorAdapter.View_Holder_Class>{

    private int resource;
    private ArrayList<Evaluador> evaluadorList;
    private Context ctx;

    public EvaluadorAdapter(ArrayList<Evaluador> evaluadorList_, int resource_, Context ctx_) {
        this.resource = resource_;
        this.evaluadorList = evaluadorList_;
        this.ctx = ctx_;
    }

    @NonNull
    @Override
    public View_Holder_Class onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new View_Holder_Class(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Class holder, int position) {
        Evaluador evaluador = evaluadorList.get(position);
        holder.txtNombre.setText(evaluador.getNombres());
        holder.txtArea.setText(evaluador.getArea());
        holder.txtCi.setText(evaluador.getIdevaluador());

        Glide.with(ctx)
                .load(evaluador.getImgjpg())
                .error(R.drawable.unknown)
                //.load("https://s22.postimg.cc/572fvlmg1/vlad-baranov-767980-unsplash.jpg")
                .into(holder.imgPerfil);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FuncionariosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                Bundle b = new Bundle();
                b.putString("id", evaluador.getIdevaluador());
                intent.putExtras(b);
                ctx.startActivity(intent);
                //Toast.makeText(ctx,evaluador.getNombres(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return evaluadorList.size();
    }


    public class View_Holder_Class extends RecyclerView.ViewHolder{
        private TextView txtNombre;
        private TextView txtCi;
        private TextView txtArea;
        private ImageView imgPerfil;

        public View view;

        public View_Holder_Class(View view){
            super(view);
            this.imgPerfil = (ImageView) view.findViewById(R.id.imgViewPerfilE);
            this.txtNombre = (TextView) view.findViewById(R.id.txtViewNombreE);
            this.txtCi = (TextView) view.findViewById(R.id.txtviewCiE);
            this.txtArea = (TextView) view.findViewById(R.id.txtviewAreaE);
        }
    }


}
