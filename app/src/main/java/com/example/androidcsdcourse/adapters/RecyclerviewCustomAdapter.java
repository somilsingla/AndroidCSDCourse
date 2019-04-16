package com.example.androidcsdcourse.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcsdcourse.R;
import com.example.androidcsdcourse.models.SocialItem;

import java.util.List;


public class RecyclerviewCustomAdapter extends RecyclerView.Adapter<RecyclerviewCustomAdapter.ViewHolder> {

    List<SocialItem> items;
    Activity context;

    public RecyclerviewCustomAdapter(Activity context, List<SocialItem> items){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Inflate view and create ViewHolder
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.social_item_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SocialItem item = items.get(position);
        holder.desc.setText(item.getDesc());
        holder.type.setText(item.getType());
        holder.desc.setText(item.getDesc());
        holder.icon.setImageResource(item.getImage_resource_id());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            TextView desc;
            TextView type;
            ImageView icon;
            public ViewHolder(View v) {
                super(v);
                name = v.findViewById(R.id.name);
                type = v.findViewById(R.id.type);
                desc = v.findViewById(R.id.description);
                icon = v.findViewById(R.id.app_icon);
            }
        }

}
