package com.project.orvbaapp11.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.orvbaapp11.R;
import com.project.orvbaapp11.adapter.MechanicsListAdapter;
import com.project.orvbaapp11.models.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class SearchCallActivity extends Activity {
    private EditText searchEditText;
    private Button searchButton;
    private ListView mechanicsListView;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private List<UserProfile> mechanics;
    private MechanicsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_call);

        // Initialize the Firebase authentication and database
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Initialize the views
        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        searchButton = (Button) findViewById(R.id.search_button);
        mechanicsListView = (ListView) findViewById(R.id.mechanics_list_view);

        // Initialize the list of mechanics and the list adapter
        mechanics = new ArrayList<>();
        adapter = new MechanicsListAdapter(this, mechanics);
        mechanicsListView.setAdapter(adapter);

        // Set the click listener for the search button
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // Clear the list of mechanics
                mechanics.clear();

                // Get the search query
                final String query = searchEditText.getText().toString().trim();

                // Use Firebase database to search for mechanics with the given location
                databaseReference.child("users").orderByChild("location").equalTo(query).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                // Loop through the mechanics and add them to the list
                                dataSnapshot.getChildren();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }


            });
        }
    });
    }
}



