package com.utad.sergio.examen;

/**
 * Created by sergio on 19/12/17.
 */

public class DataHolder {

    public static DataHolder instance = new DataHolder();

    public FireBaseAdmin fireBaseAdmin;

    public DataHolder(){
        fireBaseAdmin=new FireBaseAdmin();
    }
}
