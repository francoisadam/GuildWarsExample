package com.example.francois.guildwarsexample.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francois.guildwarsexample.R;
import com.example.francois.guildwarsexample.pojo.Achievement;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementDetailsFragment extends Fragment {
    @BindView(R.id.achievement_icon) ImageView achievementIcon;
    @BindView(R.id.achievement_name) TextView achievementName;
    @BindView(R.id.achievement_description) TextView achievementDescription;
    @BindView(R.id.achievement_requirements) TextView achievementRequirement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.achievement_details_layout, container, false);
        ButterKnife.bind(this, view);
        Achievement ach = this.getArguments().getParcelable("selectedAchievement");
        Picasso.with(this.getActivity()).load(this.getArguments().getString("selectedCategoryIcon")).into(achievementIcon);
        achievementName.setText(ach.getName());
        achievementDescription.setText(ach.getDescription());
        achievementRequirement.setText(ach.getRequirement());
        return view;
    }
}
