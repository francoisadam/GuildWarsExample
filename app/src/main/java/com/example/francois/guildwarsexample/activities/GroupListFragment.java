package com.example.francois.guildwarsexample.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.francois.guildwarsexample.R;
import com.example.francois.guildwarsexample.adapters.GroupAdapter;
import com.example.francois.guildwarsexample.pojo.Group;
import com.example.francois.guildwarsexample.retrofit.RestManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListFragment extends Fragment {
    private List<Group> groups = new ArrayList<>();
    private RestManager restManager = RestManager.getInstance();
    private GroupAdapter adapter;
    @BindView(R.id.group_list) ListView group_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new GroupAdapter(this.getActivity(), R.layout.group_layout,groups);
        collectData();
    }

    public void collectData(){
        restManager.loadGroups().subscribe(
                (List<Group> groupList) -> {
                    groups.clear();
                    groups.addAll(groupList);
                    adapter.notifyDataSetChanged();
                },
                (Throwable e) -> {},
                () -> {}
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.group_list_layout, container, false);
        ButterKnife.bind(this, view);
        group_List.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        group_List.setClickable(true);
        group_List.setOnItemClickListener(
                (AdapterView<?> arg0, View arg1, int position, long arg3) ->  {
                    CategoryListFragment fragment = new CategoryListFragment();
                    Bundle extra = new Bundle();
                    extra.putParcelable("selectedGroup",groups.get(position));
                    fragment.setArguments(extra);
                    ((MainActivity) getActivity()).changeFragment(fragment,true);
                });
    }
}
