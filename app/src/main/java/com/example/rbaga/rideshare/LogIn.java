package com.example.rbaga.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.text.TextUtils;


import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;

import com.example.rbaga.rideshare.R;

/**
 * Created by Chris.
 */



public class LogIn extends AppCompatActivity implements View.OnClickListener {

    //declare Firebase mAuth
    private FirebaseAuth mAuth;
    private EditText textEmail;
    private EditText textPassword;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUpButton).setOnClickListener(this);

        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);

        email = textEmail.getText().toString();
        password = textPassword.getText().toString();

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.signUpButton:
                startActivity(new Intent(this, SignUp.class));
                break;
        }

    }

    private void logIn(String email, String password) {


    }

    public boolean validateLogIn() {
        boolean valid = true;

        String email = textEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            textEmail.setError("Required.");
            valid = false;
        } else {
            textEmail.setError(null);
        }

        String password = textPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            textPassword.setError("Required.");
            valid = false;
        } else {
            textPassword.setError(null);
        }

        return valid;
    }

}