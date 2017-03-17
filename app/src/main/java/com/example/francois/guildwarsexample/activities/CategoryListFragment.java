package com.example.francois.guildwarsexample.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.francois.guildwarsexample.R;
import com.example.francois.guildwarsexample.adapters.CategoryAdapter;
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
        restManager.loadCategories(selectedGroup.getCategories())
                .subscribe((List<Category> categories) -> {
                            categoryList.clear();
                            categoryList.addAll(categories);
                            categoryAdapter.notifyDataSetChanged();
                        },
                        (error) -> {},
                        () -> {});
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

