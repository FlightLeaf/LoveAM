package com.stop.loveam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.stop.loveam.R;
import com.stop.loveam.activity.SearchActivity;


public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment followFragment = new FollowFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.layout_home_child, followFragment).commit();

        TabLayout tabLayout = view.findViewById(R.id.tab_layout_);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: " + tab.getPosition());
                if (tab.getPosition() == 0) {
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_home_child, new FollowFragment()).commit();
                } else if (tab.getPosition() == 1) {
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_home_child, new RecommendFragment()).commit();
                } else if (tab.getPosition() == 2) {
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_home_child, new VideoFragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // 如果需要处理未选中的Tab，可以在这里添加逻辑
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 如果需要在Tab重新选中时执行某些操作，可以在这里添加逻辑
            }
        });

        ImageView imageView = view.findViewById(R.id.search_image);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });
    }

}

