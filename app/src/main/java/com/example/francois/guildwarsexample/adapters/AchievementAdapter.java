package com.example.francois.guildwarsexample.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francois.guildwarsexample.R;
import com.example.francois.guildwarsexample.pojo.Achievement;
import com.example.francois.guildwarsexample.pojo.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by francois on 07/03/2017.
 */

public class AchievementAdapter extends ArrayAdapter<Achievement> {
    private Context context;
    private int layoutResourceId;
    public List<Achievement> data;
    private Category selectedCategory;

    public AchievementAdapter(Context context, int layoutResourceId, List<Achievement> data, Category selectedCategory) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.selectedCategory=selectedCategory;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AchievementHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new AchievementHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (AchievementHolder)row.getTag();
        }
        Achievement achievement = data.get(position);
        Picasso.with(context).load(selectedCategory.getIcon()).into(holder.achievementIcon);
        holder.achievementName.setText(achievement.getName());
        holder.achievementDescription.setText(achievement.getDescription());

        return row;
    }

    class AchievementHolder
    {
        @BindView(R.id.achievement_icon) ImageView achievementIcon;
        @BindView(R.id.achievement_name) TextView achievementName;
        @BindView(R.id.achievement_description) TextView achievementDescription;

        private AchievementHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
