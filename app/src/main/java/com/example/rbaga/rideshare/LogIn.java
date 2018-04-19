package com.example.rbaga.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Chris.
 */



public class LogIn extends AppCompatActivity implements View.OnClickListener {

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
        findViewById(R.id.logInButton).setOnClickListener(this);

        textEmail = findViewById(R.id.emailLoginText);
        textPassword = findViewById(R.id.passwordLoginText);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.signUpButton:
                startActivity(new Intent(this, SignUp.class));
                break;

            case R.id.logInButton:
                email = textEmail.getText().toString();
                password = textPassword.getText().toString();

                logIn(email, password);
                break;
        }

    }

    private void logIn(String email, String password) {

        //validate the login details
        if (!validateLogIn()) {
            return;
        }
        //
        //start sign in
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //log in success
                            Toast.makeText(LogIn.this, "Log In successful.",
                                    Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            //start activity MainMenu

                            startActivity(new Intent(LogIn.this, MainMenu.class));

                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(LogIn.this, "Log In failed - please check your credentials and internet connection.",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
    //.
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