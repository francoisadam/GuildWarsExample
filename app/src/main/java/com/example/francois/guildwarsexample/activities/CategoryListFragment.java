package com.example.francois.guildwarsexample.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.francois.guildwarsexample.R;
import com.example.francois.guildwarsexample.adapters.CategoryAdapter;
import com.example.francois.guildwarsexample.database.DBManager;
import com.example.francois.guildwarsexample.pojo.Category;
import com.example.francois.guildwarsexample.pojo.Group;
import com.example.francois.guildwarsexample.retrofit.RestManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListFragment extends Fragment {
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    private Group selectedGroup;
    private RestManager restManager = RestManager.getInstance();
    private DBManager dbManager = DBManager.getInstance();
    @BindView(R.id.category_list) ListView categoryListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryList = new ArrayList<>();
        selectedGroup = this.getArguments().getParcelable("selectedGroup");
        categoryAdapter = new CategoryAdapter(this.getActivity(), R.layout.category_layout ,categoryList);
        collectData();
    }

    public void collectData() {
        //TODO Dans le cas en ligne, envoyer les données reçu à la base de donnée sans pour autant ralentir le reste

        ConnectivityManager cm = (ConnectivityManager)this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            restManager.loadCategories(selectedGroup.getCategories())
                    .subscribe((List<Category> categories) -> {
                                categoryList.clear();
                                categoryList.addAll(categories);
                                categoryAdapter.notifyDataSetChanged();
                            },
                            (error) -> {},
                            () -> {});
        }
        else {
            categoryList.clear();
            categoryList.addAll(dbManager.loadCategories(selectedGroup.getCategories()));
            categoryAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.category_list_layout, container, false);
        ButterKnife.bind(this, view);
        categoryListView.setAdapter(categoryAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        categoryListView.setOnItemClickListener(
                (AdapterView<?> parent, View v, int position, long id) -> {
                    AchievementListFragment fragment = new AchievementListFragment();
                    Bundle extra = new Bundle();
                    extra.putParcelable("selectedCategory", categoryList.get(position));
                    fragment.setArguments(extra);
                    ((MainActivity) getActivity()).changeFragment(fragment,true);
                }
        );
    }
}

