package com.project.orvbaapp11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.orvbaapp11.R;

import java.util.List;

public class FeedbackListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> feedback;

    public FeedbackListAdapter(Context context, List<String> feedback) {
        super(context, R.layout.item_feedback, feedback);
        this.context = context;
        this.feedback = feedback;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Inflate the view for the list item
        View rowView = inflater.inflate(R.layout.item_feedback, parent, false);

        // Get the views for the list item
        TextView feedbackTextView = (TextView) rowView.findViewById(R.id.feedback_text_view);

        // Get the feedback at the given position
        String feedback = this.feedback.get(position);

        // Set the text for the views
        feedbackTextView.setText(feedback);

        return rowView;
    }
}

