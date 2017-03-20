package com.example.francois.guildwarsexample.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.francois.guildwarsexample.R;
import com.example.francois.guildwarsexample.adapters.AchievementAdapter;
import com.example.francois.guildwarsexample.database.DBManager;
import com.example.francois.guildwarsexample.pojo.Achievement;
import com.example.francois.guildwarsexample.pojo.Category;
import com.example.francois.guildwarsexample.retrofit.RestManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementListFragment extends Fragment {
    private List<Achievement> achievements = new ArrayList<>();
    private RestManager restManager = RestManager.getInstance();
    private DBManager dbManager = DBManager.getInstance();
    private Category selectedCategory;
    private AchievementAdapter adapter;
    @BindView(R.id.achievement_list) ListView achievement_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedCategory = this.getArguments().getParcelable("selectedCategory");
        adapter = new AchievementAdapter(this.getActivity(),R.layout.achievement_layout,achievements,selectedCategory);
        collectData();
    }

    public void collectData(){
        ConnectivityManager cm = (ConnectivityManager)this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            restManager.loadAchievements(selectedCategory.getAchievements()).subscribe(
                    (List<Achievement> ach) -> {
                        achievements.clear();
                        achievements.addAll(ach);
                        dbManager.addAchievements(ach);
                        adapter.notifyDataSetChanged();
                    },
                    (Throwable e) -> {},
                    () -> {}
            );
        }
        else {
            achievements.clear();
            achievements.addAll(dbManager.loadAchievements(selectedCategory.getAchievements()));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.achievement_list_layout, container, false);
        ButterKnife.bind(this, view);
        achievement_List.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        achievement_List.setClickable(true);
        achievement_List.setOnItemClickListener(
                (AdapterView<?> arg0, View arg1, int position, long arg3) ->  {
            AchievementDetailsFragment fragment = new AchievementDetailsFragment();
            Bundle extra = new Bundle();
            extra.putParcelable("selectedAchievement",achievements.get(position));
            extra.putString("selectedCategoryIcon",selectedCategory.getIcon());
            fragment.setArguments(extra);
            ((MainActivity) getActivity()).changeFragment(fragment,true);
        });
    }
}
