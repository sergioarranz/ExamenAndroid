package com.utad.sergio.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public TextView tvWelcomeMsg;
    public Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events = new SecondActivityEvents(this);
        btnSignOut = this.findViewById(R.id.btnSignOut);
        btnSignOut.setText(R.string.btnsignout);
        btnSignOut.setOnClickListener(events);

        tvWelcomeMsg = this.findViewById(R.id.tvWelcomeMsg);
        tvWelcomeMsg.setText(R.string.welcomemsg);
    }
}

class SecondActivityEvents implements View.OnClickListener {
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
}
