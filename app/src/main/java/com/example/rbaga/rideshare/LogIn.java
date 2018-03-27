package com.example.rbaga.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.rbaga.rideshare.R;

/**
 * Created by Chris.
 */



public class LogIn extends AppCompatActivity implements View.OnClickListener {

    //declare Firebase mAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        findViewById(R.id.signUpButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.signUpButton:
                startActivity(new Intent(this, SignUp.class));
                break;
        }

    }

}