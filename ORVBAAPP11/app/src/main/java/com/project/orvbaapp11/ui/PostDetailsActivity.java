package com.project.orvbaapp11.ui;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.orvbaapp11.R;

public class PostDetailsActivity extends Activity {
    private EditText nameEditText;
    private EditText locationEditText;
    private EditText servicesEditText;
    private Button postButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        // Initialize the Firebase authentication and database
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the current user
        currentUser = firebaseAuth.getCurrentUser();

        // Initialize the views
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        locationEditText = (EditText) findViewById(R.id.location_edit_text);
        servicesEditText = (EditText) findViewById(R.id.services_edit_text);
        postButton = (Button) findViewById(R.id.post_button);

        // Set the click listener for the post button
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user's input
                String name = nameEditText.getText().toString().trim();
                String location = locationEditText.getText().toString().trim();
                String services = servicesEditText.getText().toString().trim();

                // Use Firebase database to store the user's information
                databaseReference.child("users").child(currentUser.getUid()).child("name").setValue(name);
                databaseReference.child("users").child(currentUser.getUid()).child("location").setValue(location);
                databaseReference.child("users").child(currentUser.getUid()).child("services").setValue(services);

                // Show a toast message to confirm that the information was posted
                Toast.makeText(PostDetailsActivity.this, "Information posted", Toast.LENGTH_SHORT).show();

                // Clear the input fields
                nameEditText.setText("");
                locationEditText.setText("");
                servicesEditText.setText("");
            }
        });
    }
}
