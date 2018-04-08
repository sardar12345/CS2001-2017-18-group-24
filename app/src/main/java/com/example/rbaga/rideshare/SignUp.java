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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Chris.
 */

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //sign up widgets
    private EditText emailText;
    private EditText usernameText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private ProgressBar progressBar;

    //firebase auth object
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        emailText = findViewById(R.id.textEmail);
        usernameText = findViewById(R.id.textUsername);
        passwordText = findViewById(R.id.textPassword);
        confirmPasswordText = findViewById(R.id.textConfirmPassword);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUp).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.signUp:
                registerUser();

                break;

            case R.id.backButton:
                startActivity(new Intent(this, LogIn.class));
                finish();
                break;
        }

    }

    //includes the following data validation:
    //required fields entered?
    //correct length fields?
    //correct type entry?
    //passwords match?
    private void registerUser() {
        final String email = emailText.getText().toString().trim();
        final String username = usernameText.getText().toString().trim();
        final String password = passwordText.getText().toString().trim();
        String confirmPassword = confirmPasswordText.getText().toString().trim();

        if(email.isEmpty()) {
            emailText.setError("Email is required");
            emailText.requestFocus();
            return;
        }

        if(username.isEmpty()) {
            usernameText.setError("Username is required");
            usernameText.requestFocus();
            return;
        }

        if(username.length() > 25) {
            usernameText.setError("Maximum username length is 25");
            usernameText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordText.setError("Password is required");
            passwordText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Please enter a valid email");
            emailText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordText.setError("Minimum password length is 6");
            passwordText.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordText.setError("Passwords do not match");
            confirmPasswordText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    AuthUser(email, password);
                    AddUserToDatabase(username, email);
                }

                else {
                    Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void AuthUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //authentication success, display toast
                            Toast.makeText(getApplicationContext(), "User Registration Successful", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            // If authentication fails, display a toast to the user
                            Toast.makeText(SignUp.this, "Sign up authentication failed",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    private void AddUserToDatabase(String username, String email) {


    }



}
