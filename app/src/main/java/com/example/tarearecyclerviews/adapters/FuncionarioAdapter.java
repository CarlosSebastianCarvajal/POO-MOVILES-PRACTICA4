package com.example.tarearecyclerviews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tarearecyclerviews.R;
import com.example.tarearecyclerviews.models.Evaluador;
import com.example.tarearecyclerviews.models.Funcionario;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FuncionarioAdapter extends RecyclerView.Adapter<FuncionarioAdapter.View_Holder_Class>{

    private int resource;
    private ArrayList<Funcionario> funcionarioList;
    private Context ctx;

    public FuncionarioAdapter(ArrayList<Funcionario> funcionarioList, int resource, Context ctx) {
        this.resource = resource;
        this.funcionarioList = funcionarioList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public View_Holder_Class onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new View_Holder_Class(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Class holder, int position) {
        Funcionario funcionario = funcionarioList.get(position);

        holder.txtNombre.setText(funcionario.getNombres());
        holder.txtCi.setText(funcionario.getIdevaluado());
        holder.txtGenero.setText(funcionario.getGenero());
        holder.txtSituacion.setText(funcionario.getSituacion());
        holder.txtCargo.setText(funcionario.getCargo());

        Glide.with(ctx)
                .load(funcionario.getImgjpg())
                .error(R.drawable.unknown)
                //.load("https://s22.postimg.cc/572fvlmg1/vlad-baranov-767980-unsplash.jpg")
                .into(holder.imgPerfil);
    }

    @Override
    public int getItemCount() {
        return funcionarioList.size();
    }


    public class View_Holder_Class extends RecyclerView.ViewHolder{
        private TextView txtNombre;
        private TextView txtCi;
        private TextView txtGenero;
        private TextView txtSituacion;
        private TextView txtCargo;
        private ImageView imgPerfil;

        public View view;

        public View_Holder_Class(View view){
            super(view);
            this.imgPerfil = (ImageView) view.findViewById(R.id.imgViewPerfilF);
            this.txtNombre = (TextView) view.findViewById(R.id.txtViewNombreF);
            this.txtCi = (TextView) view.findViewById(R.id.txtviewCiF);
            this.txtGenero = (TextView) view.findViewById(R.id.txtviewGeneroF);
            this.txtSituacion = (TextView) view.findViewById(R.id.txtviewSituacionF);
            this.txtCargo = (TextView) view.findViewById(R.id.txtviewCargoF);
        }
    }
}
