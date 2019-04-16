package com.example.androidcsdcourse.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidcsdcourse.R;
import com.example.androidcsdcourse.models.Item;

import java.util.List;



public class CustomListAdapter extends ArrayAdapter<Item> {
    List<Item> items;
    Activity context;

    public CustomListAdapter(@NonNull Activity context, @NonNull List<Item> objects) {
        super(context, R.layout.list_item, objects);
        this.context = context;
        items = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.list_item, null, false);
        TextView name = row.findViewById(R.id.name);
        ImageView image = row.findViewById(R.id.image);
        TextView desc = row.findViewById(R.id.desc);
        name.setText(items.get(position).getName());
        image.setImageResource(items.get(position).getImage_id());
        desc.setText(items.get(position).getDetails());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Index" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }
}
