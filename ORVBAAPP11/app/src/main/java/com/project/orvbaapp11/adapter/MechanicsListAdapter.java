package com.project.orvbaapp11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.orvbaapp11.R;
import com.project.orvbaapp11.models.UserProfile;

import java.util.List;

public class MechanicsListAdapter extends ArrayAdapter<UserProfile> {
    private final Context context;
    private final List<UserProfile> mechanics;

    public MechanicsListAdapter(Context context, List<UserProfile> mechanics) {
        super(context, R.layout.item_mechanic, mechanics);
        this.context = context;
        this.mechanics = mechanics;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Inflate the view for the list item
        View rowView = inflater.inflate(R.layout.item_mechanic, parent, false);

        // Get the views for the list item
        TextView nameTextView = (TextView) rowView.findViewById(R.id.name_text_view);
        TextView locationTextView = (TextView) rowView.findViewById(R.id.location_text_view);
        TextView servicesTextView = (TextView) rowView.findViewById(R.id.services_text_view);

        // Get the mechanic at the given position
        UserProfile mechanic = mechanics.get(position);

        // Set the text for the views
        nameTextView.setText(mechanic.getName());
        locationTextView.setText(mechanic.getLocation());
        servicesTextView.setText(mechanic.getServices());

        return rowView;
    }
}

