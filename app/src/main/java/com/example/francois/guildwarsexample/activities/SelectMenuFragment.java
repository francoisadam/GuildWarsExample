package com.example.francois.guildwarsexample.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.francois.guildwarsexample.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectMenuFragment extends Fragment {

    @BindView(R.id.button) Button button;
    @BindView(R.id.imageView) ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.select_menu_layout, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(this.getActivity()).load(R.drawable.guild_wars_2_logo).into(image);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        button.setOnClickListener(
                (View v) -> ((MainActivity) getActivity()).changeFragment(new GroupListFragment(), true)
        );
    }
}
