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
import com.example.francois.guildwarsexample.pojo.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private int layoutResourceId;
    public List<Category> data;

    public CategoryAdapter(Context context, int layoutResourceId, List<Category> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        GroupHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new GroupHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (GroupHolder)row.getTag();
        }
        Category category = data.get(position);
        Picasso.with(context).load(category.getIcon()).into(holder.categoryIcon);
        holder.categoryName.setText(category.getName());
        holder.categoryDescription.setText(category.getDescription());

        return row;
    }

    class GroupHolder
    {
        @BindView(R.id.category_icon) ImageView categoryIcon;
        @BindView(R.id.category_name) TextView categoryName;
        @BindView(R.id.category_description) TextView categoryDescription;

        private GroupHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}