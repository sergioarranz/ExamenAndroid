package com.utad.sergio.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;
import com.utad.sergio.examen.adapter.ListAdapter;
import com.utad.sergio.examen.entity.Noticia;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private SecondActivityEvents events;
    private ListaFragment listaFragment;
    public TextView tvWelcomeMsg;
    public Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setFirebaseAdminListener(events);
        this.listaFragment = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        btnSignOut = this.findViewById(R.id.btnSignOut);
        btnSignOut.setText(R.string.btnsignout);
        btnSignOut.setOnClickListener(events);

        tvWelcomeMsg = this.findViewById(R.id.tvWelcomeMsg);
        tvWelcomeMsg.setText(R.string.welcomemsg);

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");
    }
    public ListaFragment getListFragment() {
        return listaFragment;
    }
}

class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener {
    SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == this.secondActivity.btnSignOut.getId()) {
            Intent intent= new Intent(secondActivity,MainActivity.class);
            secondActivity.startActivity(intent);
            secondActivity.finish();
        }
    }

    @Override
    public void firebaseAdmin_registerOk(boolean blOk) {

    }

    @Override
    public void firebaseAdmin_loginOk(boolean blOk) {

    }

    @Override
    public void firebaseAdmin_ramaDescargada(String rama, DataSnapshot dataSnapshot) {

        if(rama.equals("Noticias")){
            GenericTypeIndicator<ArrayList<Noticia>> indicator = new GenericTypeIndicator<ArrayList<Noticia>>(){};
            ArrayList<Noticia> arrNoticias = dataSnapshot.getValue(indicator);
            this.secondActivity.getListFragment().getMyLista().setAdapter(new ListAdapter(arrNoticias));


        }
    }
}
