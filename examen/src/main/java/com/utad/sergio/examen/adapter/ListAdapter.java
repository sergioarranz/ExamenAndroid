package com.utad.sergio.examen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utad.sergio.examen.R;
import com.utad.sergio.examen.entity.Noticia;

import java.util.ArrayList;

/**
 * Created by sergio on 19/12/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<Noticia> contenidoLista;

    public ListAdapter(ArrayList<Noticia> contenidoLista) {
        System.out.println("contenidoLista: " + contenidoLista);
        this.contenidoLista = contenidoLista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_layout, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.getTxtTitulo().setText(this.getContenidoLista().get(position).titulo);
        holder.getTxtContenido().setText(this.getContenidoLista().get(position).contenido);
    }

    @Override
    public int getItemCount() {
        return this.getContenidoLista().size();
    }


    public ArrayList<Noticia> getContenidoLista() {
        return contenidoLista;
    }

    public void setContenidoLista(ArrayList<Noticia> contenidoLista) {
        this.contenidoLista = contenidoLista;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitulo;
        public TextView txtContenido;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            this.txtContenido = (TextView) itemView.findViewById(R.id.txtContenido);
        }

        public TextView getTxtTitulo() {
            return txtTitulo;
        }

        public void setTxtTitulo(TextView txtTitulo) {
            this.txtTitulo = txtTitulo;
        }

        public TextView getTxtContenido() {
            return txtContenido;
        }

        public void setTxtContenido(TextView txtContenido) {
            this.txtContenido = txtContenido;
        }
    }
}