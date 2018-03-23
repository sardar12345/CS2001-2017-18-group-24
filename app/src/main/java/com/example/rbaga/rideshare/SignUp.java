package com.example.rbaga.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rbaga.rideshare.MainMenu;
import com.example.rbaga.rideshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Chris.
 */

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //sign up widgets
    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private ProgressBar progressBar;

    //firebase auth object
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        emailText = (EditText) findViewById(R.id.textEmail);
        passwordText = (EditText) findViewById(R.id.textPassword);
        confirmPasswordText = (EditText) findViewById(R.id.textConfirmPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUp).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.signUp:

                break;

            case R.id.backButton:
                startActivity(new Intent(this, MainMenu.class));
                break;
        }

    }



}
