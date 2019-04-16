package com.example.androidcsdcourse.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androidcsdcourse.R;
import com.example.androidcsdcourse.listeners.FragmentButtonClickListener;

public class BlankFragment extends Fragment {

    FragmentButtonClickListener listener;

    public BlankFragment() {
    }

    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        ImageView icon=v.findViewById(R.id.icon);
        Button click=v.findViewById(R.id.click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked("Fragment Button Clicked" );
            }
        });

        // Getting context
        Context context = getContext();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (FragmentButtonClickListener)context;
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}
