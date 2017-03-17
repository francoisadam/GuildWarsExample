package com.example.francois.guildwarsexample.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.francois.guildwarsexample.R;
import com.example.francois.guildwarsexample.pojo.Group;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by francois on 07/03/2017.
 */

public class GroupAdapter extends ArrayAdapter<Group> {
    private Context context;
    private int layoutResourceId;
    public List<Group> data;

    public GroupAdapter(Context context, int layoutResourceId, List<Group> data) {
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
        Group group = data.get(position);
        holder.groupName.setText(group.getName());
        holder.groupDescription.setText(group.getDescription());

        return row;
    }

    class GroupHolder
    {
        @BindView(R.id.group_name) TextView groupName;
        @BindView(R.id.group_description) TextView groupDescription;

        private GroupHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
