package com.utad.sergio.examen;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by sergio on 19/12/17.
 */

public class FireBaseAdmin {

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRefRaiz;

    public FirebaseAdminListener firebaseAdminListener;
    public FirebaseUser user;

    public FireBaseAdmin(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefRaiz=database.getReference();
    }

    public void setFirebaseAdminListener(FirebaseAdminListener firebaseAdminListener){
        this.firebaseAdminListener=firebaseAdminListener;
    }

    public void regUser(String email, String password, Activity activity){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            firebaseAdminListener.firebaseAdmin_registerOk(true);
                        }else{
                            firebaseAdminListener.firebaseAdmin_registerOk(false );
                        }
                    }
                });

    }
    public void logUser(String email, String password, Activity activity) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    firebaseAdminListener.firebaseAdmin_loginOk(true);
                } else {
                    firebaseAdminListener.firebaseAdmin_loginOk(false);
                }
            }
        });

    }
    public void descargarYObservarRama(final String rama){

        //PARA LEER CUALQUIER RAMA QUE ESTES INTERESADO EN RECIBIR SE LE PASA LA RUTA POR PARAMETRO Y YA ESTA
        DatabaseReference  refRama = myRefRaiz.child(rama);
// Read from the database
        refRama.addValueEventListener(new ValueEventListener() {
            @Override

            //dataSnapshot ES UN HASH MAP que representa la rama
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                firebaseAdminListener.firebaseAdmin_ramaDescargada(rama,dataSnapshot );

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                firebaseAdminListener.firebaseAdmin_ramaDescargada(rama,  null);
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
