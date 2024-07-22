package com.project.orvbaapp11.ui;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.orvbaapp11.R;
import com.project.orvbaapp11.adapter.FeedbackListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReadFeedbackActivity extends Activity {
    private ListView feedbackListView;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private List<String> feedback;
    private FeedbackListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_feedback);

        // Initialize the Firebase authentication and database
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Initialize the views
        feedbackListView = (ListView) findViewById(R.id.feedback_list_view);

        // Initialize the list of feedback and the list adapter
        feedback = new ArrayList<>();
        adapter = new FeedbackListAdapter(this, feedback);
        feedbackListView.setAdapter(adapter);

        // Use Firebase database to get the feedback
        databaseReference.child("feedback").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear the list of feedback
                feedback.clear();

                // Loop through the feedback and add them to the list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    feedback.add(snapshot.getValue(String.class));
                }

                // Notify the list adapter that the data has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Show an error message
                Toast.makeText(ReadFeedbackActivity.this, "Failed to load feedback", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
