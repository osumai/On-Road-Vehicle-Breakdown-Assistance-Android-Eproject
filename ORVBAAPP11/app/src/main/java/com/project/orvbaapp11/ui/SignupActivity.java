package com.project.orvbaapp11.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.orvbaapp11.R;
import com.project.orvbaapp11.models.UserProfile;

public class SignupActivity extends Activity {
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private EditText passwordEditText;
    private EditText roleEditText;
    private Button signupButton;
    private TextView login;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize the Firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize the views
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        roleEditText = (EditText) findViewById(R.id.role_edit_text);
        signupButton = (Button) findViewById(R.id.signup_button);

        // Set the click listener for the signup button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user's input
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String role = roleEditText.getText().toString().trim();

                // Create a new user profile with the given information
                UserProfile user = new UserProfile(name, email, phoneNumber, password, role, false);

                // Approve the mechanic using Firebase authentication
                user.approveMechanic(firebaseAuth);

                // Clear the input fields
                nameEditText.setText("");
                emailEditText.setText("");
                phoneNumberEditText.setText("");
                passwordEditText.setText("");
                roleEditText.setText("");
            }
        });
        login=findViewById(R.id.loginAcc);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
