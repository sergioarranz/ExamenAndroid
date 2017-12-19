package com.example.milib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    public RecyclerView myLista;

    public ListaFragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        myLista = (RecyclerView) view.findViewById(R.id.myList);
        //Le seteamos al RecyclerView un Layout. En este caso le seteamos por ejemplo un GridLayout
        //Al ser un recyclerView puedo poner cualquier tipo de elemento.
        System.out.println("esto si");
        myLista.setLayoutManager(new GridLayoutManager(getContext(),2)); // spanCount es el número de columnas. Esto hace directamente que sea una colección, dado que una tabla solo tiene una columna.
        System.out.println("y esto??");

        return view;
    }

    public RecyclerView getMyLista() {
        return myLista;
    }
    public void setMilista(RecyclerView myLista) {
        this.myLista = myLista;
    }


}
