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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Chris.
 */

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //sign up widgets
    private EditText emailText;
    private EditText nameText;
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
        nameText = findViewById(R.id.textName);
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
        final String name = nameText.getText().toString().trim();
        final String password = passwordText.getText().toString().trim();
        String confirmPassword = confirmPasswordText.getText().toString().trim();

        if(email.isEmpty()) {
            emailText.setError("Email is required");
            emailText.requestFocus();
            return;
        }

        if(name.isEmpty()) {
            nameText.setError("Name is required");
            nameText.requestFocus();
            return;
        }

        if(name.length() > 35) {
            nameText.setError("Maximum name length is 35");
            nameText.requestFocus();
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

        CreateUser(email, password, name);

    }

    private void AuthUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            //if authentication fails here, display toast
                            Toast.makeText(getApplicationContext(), "Sign up authentication failed", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    private void AddUserToDatabase(String name, String email) {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserID = currentFirebaseUser.getUid();

        User user = new User();
        user.setName(name);
        user.setEmail(email);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("users").child(currentUserID).setValue(user)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //display a toast based on whether or not the user was added to the database successfully
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User registration successful", Toast.LENGTH_LONG).show();
                            StartMainMenu();
                            finish();
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Failed to add user to database", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void StartMainMenu() {
        startActivity(new Intent(this, MainMenu.class));
    }

    private void CreateUser(final String email, final String password, final String name) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    AuthUser(email, password);
                    AddUserToDatabase(name, email);
                }

                else {
                    Toast.makeText(getApplicationContext(), "An error occurred - email may be in use", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
