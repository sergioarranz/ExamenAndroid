package com.utad.sergio.examen;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment=(LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);
        registerFragment=(RegisterFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentRegister);

        //INICIALIZAMOS FIREBASE
        //fireBaseAdmin = new FireBaseAdmin();
        //INICIALIZAMOS EL MAINACTIVITYEVENTS
        MainActivityEvents mainActivityEvents= new MainActivityEvents(this);

        //PARA QUE EL MAINACTIVITY ESCUCHE DEL LOGINFRAGMENT,REGISTER Y FIREBASEADMIN
        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        DataHolder.instance.fireBaseAdmin.setFirebaseAdminListener(mainActivityEvents);

        //CREAMOS LAS TRANSICIONES

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //PARA QUE NOS SALGA PRIMERO EL LOGIN
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();

        //  SALTARSE LOGIN
        //DataHolder.instance.fireBaseAdmin.loginEmailPass("sergioas1996@gmail.com","123456",this);
    }
}

class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener,FirebaseAdminListener {
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    // Register --> Login --> SecondActivity (User registers)
    @Override
    public void OnRFAccBtnClicked(String sUser, String sPass) {
        DataHolder.instance.fireBaseAdmin.regUser(sUser,sPass,mainActivity);
    }
    // Register --> Back to Main Activity (User cancels register)
    @Override
    public void OnRFCancelBtnClicked() {
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();

        transaction.show(mainActivity.loginFragment);
        transaction.hide(mainActivity.registerFragment);
        transaction.commit();
    }
    // Login --> Second Activity (User Login successful)
    @Override
    public void OnLFLoginBtnClicked(String sUser, String sPass) {
        DataHolder.instance.fireBaseAdmin.logUser(sUser,sPass,mainActivity);
    }
    // Login --> RegisterFragment (sends user to Register View)
    @Override
    public void OnLFRegBtnClicked() {
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();

        transaction.hide(mainActivity.loginFragment);
        transaction.show(mainActivity.registerFragment);
        transaction.commit();
    }

    @Override
    public void firebaseAdmin_registerOk(boolean blOk) {
        if(blOk){
            //ESTA PARTE DE CODIGO LO QUE HARA ES INICIAR EL SEGUNDO ACTIVITY Y FINALIZAR EL MAIN
            Intent intent= new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        } else {
            Log.v("RegisterError","Registro: "+blOk);
        }
    }

    @Override
    public void firebaseAdmin_loginOk(boolean blOk) {
        if(blOk){
            //ESTA PARTE DE CODIGO LO QUE HARA ES INICIAR EL SEGUNDO ACTIVITY Y FINALIZAR EL MAIN
            Intent intent= new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        } else {
            Log.v("LoginError","Login: "+blOk);
        }
    }

    @Override
    public void firebaseAdmin_ramaDescargada(String rama, DataSnapshot dataSnapshot) {

    }
}
