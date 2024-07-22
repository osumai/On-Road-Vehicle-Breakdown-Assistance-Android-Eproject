package com.project.orvbaapp11.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.orvbaapp11.R;

public class PostFeedbackActivity extends Activity {
    private EditText feedbackEditText;
    private Button postButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feedback);

        // Initialize the Firebase authentication and database
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Initialize the views
        feedbackEditText = (EditText) findViewById(R.id.feedback_edit_text);
        postButton = (Button) findViewById(R.id.post_button);

        // Set the click listener for the post button
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the feedback text
                final String feedback = feedbackEditText.getText().toString().trim();

                // Check if the feedback is not empty
                if (!feedback.isEmpty()) {
                    // Use Firebase database to post the feedback
                    databaseReference.child("feedback").push().setValue(feedback);

                    // Show a success message
                    Toast.makeText(PostFeedbackActivity.this, "Feedback posted successfully", Toast.LENGTH_SHORT).show();

                    // Clear the feedback text
                    feedbackEditText.setText("");
                } else {
                    // Show an error message
                    Toast.makeText(PostFeedbackActivity.this, "Please enter some feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
